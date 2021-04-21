package com.example.automatic_analytics_analyser.view.fragments.medication

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.CalendarItemBinding
import com.example.automatic_analytics_analyser.databinding.MedicationItemBinding
import com.example.automatic_analytics_analyser.model.Medication
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class BindingMedicationItem(val medication: Medication) : AbstractBindingItem<MedicationItemBinding>() {
    override val type: Int
        get() = R.id.fastadapter_item

    override fun bindView(binding: MedicationItemBinding, payloads: List<Any>) {
        binding.medication = medication
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): MedicationItemBinding {
        return MedicationItemBinding.inflate(inflater, parent, false)
    }

}