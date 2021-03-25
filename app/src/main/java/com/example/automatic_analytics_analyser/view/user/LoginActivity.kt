package com.example.automatic_analytics_analyser.view.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityLoginBinding
import com.example.automatic_analytics_analyser.view.MainActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {
    private val TAG = "LoginActivity"

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.registerTitle.setOnClickListener {
            startActivityForResult(Intent(this, RegisterActivity::class.java), 40)
        }

        viewModel.loginCompleted.observe(this, Observer {
            if (!it.isNullOrEmpty()){
                //TODO: Store token in preferences using repository
                Log.v(TAG, "Navigating to main screen after logging in")
                startActivity(Intent(this, MainActivity::class.java))
            }
        })

    }
}