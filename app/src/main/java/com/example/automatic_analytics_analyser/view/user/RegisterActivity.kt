package com.example.automatic_analytics_analyser.view.user

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityRegisterBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RegisterActivity : DaggerAppCompatActivity()
{
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: ResgisterViewModel
        get() {
            return ViewModelProviders.of(this, viewModelFactory).get(ResgisterViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.registerError.observe(this, Observer {
//            when(it){
//                ResgisterViewModel.ErrorType.DNI
//            }
        })

    }
}