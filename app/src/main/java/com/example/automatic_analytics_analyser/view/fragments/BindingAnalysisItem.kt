package com.example.automatic_analytics_analyser.view.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.AnalysisItemBinding
import com.example.automatic_analytics_analyser.model.Analysis
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class BindingAnalysisItem(val analysis: Analysis) : AbstractBindingItem<AnalysisItemBinding>() {
    override val type: Int
        get() = R.id.fastadapter_item

    override fun bindView(binding: AnalysisItemBinding, payloads: List<Any>) {
        binding.analysis = analysis
    }


    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): AnalysisItemBinding {
        return AnalysisItemBinding.inflate(inflater, parent, false)
    }
}
