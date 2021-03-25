package com.example.automatic_analytics_analyser.view

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class AbstractActivity: DaggerAppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

}