package com.example.automatic_analytics_analyser.view.fragments

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding

abstract class AbstractRecyclerFragment<BindingType : ViewDataBinding> : AbstractFragment() {
    private lateinit var binding : BindingType

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}