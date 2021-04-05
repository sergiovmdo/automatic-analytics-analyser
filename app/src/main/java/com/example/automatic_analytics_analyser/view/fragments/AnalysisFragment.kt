package com.example.automatic_analytics_analyser.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.AnalysisItemBinding
import com.example.automatic_analytics_analyser.databinding.FragmentAnalysisBinding
import com.example.automatic_analytics_analyser.model.Analysis
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.mikepenz.fastadapter.listeners.ClickEventHook

class AnalysisFragment : AbstractFragment() {

    private val itemAdapter = ItemAdapter<BindingAnalysisItem>()
    private lateinit var binding: FragmentAnalysisBinding

    private val viewModel: AnalysisViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AnalysisViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentAnalysisBinding>(
            inflater,
            R.layout.fragment_analysis,
            container,
            false
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fastAdapter
        }

        viewModel.analysis.observe(viewLifecycleOwner, Observer {
            populateAnalysis(it)
        })

        //Functionality for consult button
        fastAdapter.addEventHook(object : ClickEventHook<BindingAnalysisItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return viewHolder.asBinding<AnalysisItemBinding> {
                    it.consultButton
                }
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<BindingAnalysisItem>,
                item: BindingAnalysisItem
            ) {
                MaterialDialog(activity!!).show {
                    title(text = "Your Title")
                    var message: String = parseAnalysisContentToText(item.analysis.analysisData)
                    message(text = message)
                    positiveButton(R.string.close)
                }
            }
        })

        //Functionality for share button
        fastAdapter.addEventHook(object : ClickEventHook<BindingAnalysisItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return viewHolder.asBinding<AnalysisItemBinding> {
                    it.shareButton
                }
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<BindingAnalysisItem>,
                item: BindingAnalysisItem
            ) {
                shareAnalysis(item.analysis)
            }

        })

        viewModel.refreshAnalysis()
    }

    private fun populateAnalysis(data: List<Analysis>) {
        binding.refreshAnalysis.isRefreshing = false
        val items = data.map { BindingAnalysisItem(it) }
        itemAdapter.setNewList(items)
    }

    inline fun <reified T : ViewBinding> RecyclerView.ViewHolder.asBinding(block: (T) -> View): View? {
        return if (this is BindingViewHolder<*> && this.binding is T) {
            block(this.binding as T)
        } else {
            null
        }
    }

    private fun parseAnalysisContentToText(content: String): String {
        var result = content.replace("\"", "")
        result = result.replace("{", "")
        result = result.replace("}", "")
        result = result.replace(",", "\n\n")

        return result
    }

    private fun shareAnalysis(analysis: Analysis) {
        val shareText = "${resources.getString(R.string.shareAnalysis)} \n\n" +
                "${resources.getString(R.string.analysisDate)} ${analysis.convertDate()} \n" +
                "${resources.getString(R.string.analysisTitle)} ${analysis.disease} \n\n" +
                "${resources.getString(R.string.analysisData)}  ${parseAnalysisContentToText(
                    analysis.analysisData
                )}"
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, shareText)
        startActivity(shareIntent)
    }
}