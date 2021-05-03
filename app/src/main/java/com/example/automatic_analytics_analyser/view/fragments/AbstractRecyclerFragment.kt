package com.example.automatic_analytics_analyser.view.fragments

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.view.fragments.analysis.AnalysisViewModel
import com.example.automatic_analytics_analyser.view.fragments.analysis.BindingAnalysisItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem

abstract class AbstractRecyclerFragment<BindingType : ViewDataBinding, AdapterType : AbstractBindingItem<ViewDataBinding>, ViewModelType : ViewModel> : AbstractFragment() {
    private lateinit var binding : BindingType
    private val itemAdapter = ItemAdapter<AdapterType>()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}