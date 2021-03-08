package com.example.automatic_analytics_analyser.view

import android.os.Bundle
import android.os.PersistableBundle
import com.example.automatic_analytics_analyser.R
import dagger.android.support.DaggerAppCompatActivity

class RegisterActivity : DaggerAppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}