package com.example.automatic_analytics_analyser.view.fragments

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
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.AnalysisItemBinding
import com.example.automatic_analytics_analyser.databinding.CalendarItemBinding
import com.example.automatic_analytics_analyser.databinding.FragmentCalendarBinding
import com.example.automatic_analytics_analyser.model.Appointment
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.mikepenz.fastadapter.listeners.ClickEventHook

class CalendarFragment : AbstractFragment() {
    private val itemAdapter = ItemAdapter<BindingCalendarItem>()
    private lateinit var binding: FragmentCalendarBinding

    private val viewModel: CalendarViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CalendarViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentCalendarBinding>(
            inflater,
            R.layout.fragment_calendar,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Move to AbstractRecyclerFragment?
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fastAdapter
        }

        viewModel.appointments.observe(viewLifecycleOwner, Observer {
            populateCalendar(it)
        })

        fastAdapter.addEventHook(object : ClickEventHook<BindingCalendarItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return viewHolder.asBinding<CalendarItemBinding> {
                    it.claimButton
                }
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<BindingCalendarItem>,
                item: BindingCalendarItem
            ) {
                MaterialDialog(activity!!).show {
                    MaterialDialog(activity!!).show {
                        title(R.string.claimTitle)
                        dateTimePicker(requireFutureDateTime = true) { _, dateTime ->
                            //TODO: Do something with the claiming date

                            MaterialDialog(activity!!).show {
                                title(R.string.claimConfirmationTitle)
                                message(R.string.claimConfirmationBody)
                            }
                        }
                    }
                }
            }
        })

        viewModel.refreshCalendar()
    }

    private fun populateCalendar(data: List<Appointment>) {
        binding.refreshCalendar.isRefreshing = false
        val items = data.map { BindingCalendarItem(it) }
        itemAdapter.setNewList(items)
    }

    inline fun <reified T : ViewBinding> RecyclerView.ViewHolder.asBinding(block: (T) -> View): View? {
        return if (this is BindingViewHolder<*> && this.binding is T) {
            block(this.binding as T)
        } else {
            null
        }
    }
}