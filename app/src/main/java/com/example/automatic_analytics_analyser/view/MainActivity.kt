package com.example.automatic_analytics_analyser.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityMainBinding
import com.example.automatic_analytics_analyser.view.fragments.DrawerActivity
import com.example.automatic_analytics_analyser.view.user.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : AbstractActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initNavigationListeners()

    }

    private fun initNavigationListeners() {
        binding.analysisButton.setOnClickListener{
            val intent: Intent = Intent(this, DrawerActivity::class.java)
            intent.putExtra("fragmentId", R.id.analysisFragment)
            startActivity(intent)
        }

        binding.medicationButton.setOnClickListener{
            val intent: Intent = Intent(this, DrawerActivity::class.java)
            intent.putExtra("fragmentId", R.id.medicationFragment)
            startActivity(intent)
        }

        binding.calendarButton.setOnClickListener{
            val intent: Intent = Intent(this, DrawerActivity::class.java)
            intent.putExtra("fragmentId", R.id.calendarFragment)
            startActivity(intent)
        }

        binding.chatButton.setOnClickListener{
            val intent: Intent = Intent(this, DrawerActivity::class.java)
            intent.putExtra("fragmentId", R.id.chatFragment)
            startActivity(intent)
        }

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}