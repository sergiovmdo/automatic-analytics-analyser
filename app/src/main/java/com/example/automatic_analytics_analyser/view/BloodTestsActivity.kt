package com.example.automatic_analytics_analyser.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityBloodTestsBinding

class BloodTestsActivity : AbstractActivity() {
    private lateinit var binding : ActivityBloodTestsBinding

    private val viewModel: BloodTestsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(BloodTestsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_blood_tests)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}