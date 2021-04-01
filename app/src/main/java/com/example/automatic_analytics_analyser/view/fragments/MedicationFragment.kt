package com.example.automatic_analytics_analyser.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.FragmentMedicationBinding

class MedicationFragment : AbstractFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentMedicationBinding>(
            inflater,
            R.layout.fragment_medication,
            container,
            false
        ).root
    }
}