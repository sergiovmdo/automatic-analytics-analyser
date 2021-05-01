package com.example.automatic_analytics_analyser.view.fragments.calendar

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
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.CalendarItemBinding
import com.example.automatic_analytics_analyser.databinding.FragmentCalendarBinding
import com.example.automatic_analytics_analyser.databinding.MedicationItemBinding
import com.example.automatic_analytics_analyser.model.Appointment
import com.example.automatic_analytics_analyser.model.Medication
import com.example.automatic_analytics_analyser.view.fragments.AbstractFragment
import com.example.automatic_analytics_analyser.view.fragments.DrawerActivity
import com.example.automatic_analytics_analyser.view.fragments.medication.BindingMedicationItem
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
        })

        //Functionality for share button
        fastAdapter.addEventHook(object : ClickEventHook<BindingCalendarItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return viewHolder.asBinding<CalendarItemBinding> {
                    it.shareButton
                }
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<BindingCalendarItem>,
                item: BindingCalendarItem
            ) {
                shareAppointment(item.appointment)
            }

        })

        (requireActivity() as DrawerActivity).supportActionBar!!.setTitle(R.string.calendar_screen)

        viewModel.refreshCalendar()
    }

    private fun populateCalendar(data: List<Appointment>) {
        binding.refreshCalendar.isRefreshing = false
        val items = data.map {
            BindingCalendarItem(it)
        }
        if (items.isNotEmpty()) {
            binding.noDataText.visibility = View.GONE
        }
        itemAdapter.setNewList(items)
    }

    private fun shareAppointment(appointment: Appointment) {
        val shareText =
            "${resources.getString(R.string.appointmentDisease)} ${appointment.disease} " +
                    "${resources.getString(R.string.day)} ${appointment.convertDate()} " +
                    "${resources.getString(R.string.hour)} ${appointment.convertTime()} " +
                    "${resources.getString(R.string.`in`)} ${appointment.location} ."
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, shareText)
        startActivity(shareIntent)
    }

}