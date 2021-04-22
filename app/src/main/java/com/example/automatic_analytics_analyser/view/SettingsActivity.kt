package com.example.automatic_analytics_analyser.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityLoginBinding
import com.example.automatic_analytics_analyser.databinding.ActivitySettingsBinding
import com.example.automatic_analytics_analyser.view.AbstractActivity
import com.example.automatic_analytics_analyser.view.user.LoginViewModel
import javax.inject.Inject

class SettingsActivity : AbstractActivity() {

    private lateinit var binding: ActivitySettingsBinding

    private val viewModel: SettingsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val spinner = binding.languagesDropdown

        spinner.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ ->
                viewModel.changeLanguage(spinner.text.toString())
            }

        spinner.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.languages)
            )
        )

        viewModel.user.observe(this, Observer {
            spinner.setText(viewModel.getUserLanguage(), false)
        })


        viewModel.changeLanguage.observe(this, Observer {
            if (!it.isNullOrEmpty()){
                Toast.makeText(this, getString(R.string.changeLanguageMessage), Toast.LENGTH_LONG).show()
            }
        })
    }
}