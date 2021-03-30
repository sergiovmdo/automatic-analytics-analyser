package com.example.automatic_analytics_analyser.view

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class AbstractActivity: DaggerAppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var preferences: SharedPreferences;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

}