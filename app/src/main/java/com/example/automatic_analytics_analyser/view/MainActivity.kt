package com.example.automatic_analytics_analyser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.automatic_analytics_analyser.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}