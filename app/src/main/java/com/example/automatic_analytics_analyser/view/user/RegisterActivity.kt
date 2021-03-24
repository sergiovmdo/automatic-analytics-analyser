package com.example.automatic_analytics_analyser.view.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityRegisterBinding
import com.example.automatic_analytics_analyser.view.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RegisterActivity : DaggerAppCompatActivity() {
    private val TAG = "RegisterActivity"

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

        viewModel.registerCompleted.observe(this, Observer {
            if (it){
                startActivity(Intent(this, MainActivity::class.java))
                Log.v(TAG, "Navigating to main screen after registering")
            }
        })

        viewModel.registerError.observe(this, Observer {
            when (it.first) {
                ResgisterViewModel.ErrorType.DNI ->
                    binding.dniEditText.setError(it.second)
                ResgisterViewModel.ErrorType.WRONG_DNI ->
                    binding.dniEditText.setError(it.second)
                ResgisterViewModel.ErrorType.PASSWORD ->
                    binding.passwordEditText.setError(it.second)
                ResgisterViewModel.ErrorType.PASSWORD_MATCH -> {
                    binding.passwordEditText.setError(it.second)
                    binding.confirmPasswordEditText.setError(it.second)
                }
                ResgisterViewModel.ErrorType.CONFIRM_PASSWORD ->
                    binding.confirmPasswordEditText.setError(it.second)
                ResgisterViewModel.ErrorType.NAME ->
                    binding.nameEditText.setError(it.second)
                ResgisterViewModel.ErrorType.SURNAME ->
                    binding.firstSurnameEditText.setError(it.second)
                ResgisterViewModel.ErrorType.BIRTHDATE ->
                    binding.birthEditText.setError(it.second)
                ResgisterViewModel.ErrorType.WRONG_BIRTHDATE ->
                    binding.birthEditText.setError(it.second)
                ResgisterViewModel.ErrorType.CONTACT_METHOD -> {
                    binding.emailEditText.setError(it.second)
                    binding.phoneEditText.setError(it.second)
                }
                ResgisterViewModel.ErrorType.WRONG_BIRTHDATE ->
                    binding.birthEditText.setError(it.second)
                ResgisterViewModel.ErrorType.API_PROBLEM ->
                    Toast.makeText(this, it.second, Toast.LENGTH_LONG).show()
            }
        })

    }
}