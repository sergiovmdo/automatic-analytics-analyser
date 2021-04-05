package com.example.automatic_analytics_analyser.view.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.CalendarItemBinding
import com.example.automatic_analytics_analyser.model.Appointment
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class BindingCalendarItem(val appointment: Appointment) : AbstractBindingItem<CalendarItemBinding>() {
    override val type: Int
        get() = R.id.fastadapter_item

    override fun bindView(binding: CalendarItemBinding, payloads: List<Any>) {
        binding.appointment = appointment
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): CalendarItemBinding {
        return CalendarItemBinding.inflate(inflater, parent, false)
    }
}
