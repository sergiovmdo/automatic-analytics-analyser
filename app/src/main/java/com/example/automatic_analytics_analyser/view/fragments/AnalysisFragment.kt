package com.example.automatic_analytics_analyser.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.FragmentAnalysisBinding
import com.example.automatic_analytics_analyser.model.Analysis
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class AnalysisFragment : AbstractFragment() {

    private val itemAdapter = ItemAdapter<BindingAnalysisItem>()
    private lateinit var binding : FragmentAnalysisBinding

    private val viewModel: AnalysisViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AnalysisViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentAnalysisBinding>(
            inflater,
            R.layout.fragment_analysis,
            container,
            false
        ).root
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

        viewModel.refreshAnalysis()
    }

    private fun populateAnalysis(data: List<Analysis>) {
        val items = data.map { BindingAnalysisItem(it) }
        itemAdapter.setNewList(items)
    }
}