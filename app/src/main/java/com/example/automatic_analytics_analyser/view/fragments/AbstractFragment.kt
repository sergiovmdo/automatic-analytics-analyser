package com.example.automatic_analytics_analyser.view.fragments

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class AbstractFragment : DaggerFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

}