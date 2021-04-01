package com.example.automatic_analytics_analyser.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.FragmentCalendarBinding

class CalendarFragment : AbstractFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentCalendarBinding>(
            inflater,
            R.layout.fragment_calendar,
            container,
            false
        ).root
    }
}