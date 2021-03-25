package com.example.automatic_analytics_analyser.view.user

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityRegisterBinding
import com.example.automatic_analytics_analyser.model.ErrorType
import com.example.automatic_analytics_analyser.view.AbstractActivity
import com.example.automatic_analytics_analyser.view.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RegisterActivity : AbstractActivity() {
    private val TAG = "RegisterActivity"

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var preferences: SharedPreferences;

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

        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        viewModel.registerCompleted.observe(this, Observer {
            if (!it.isNullOrEmpty()){
                //TODO: Move this to the repository
                //Set the logged state on preferences
                if (preferences.getBoolean("logged", false)){
                    preferences.edit().putBoolean("logged", true).apply()
                }
                preferences.edit().putString("token", it)

                Log.v(TAG, "Navigating to main screen after registering")
                startActivity(Intent(this, MainActivity::class.java))
            }
        })

        viewModel.registerError.observe(this, Observer {
            when (it) {
                ErrorType.DNI ->
                    binding.dniEditText.setError(it.errorMessage)
                ErrorType.WRONG_DNI ->
                    binding.dniEditText.setError(it.errorMessage)
                ErrorType.PASSWORD ->
                    binding.passwordEditText.setError(it.errorMessage)
                ErrorType.PASSWORD_MATCH -> {
                    binding.passwordEditText.setError(it.errorMessage)
                    binding.confirmPasswordEditText.setError(it.errorMessage)
                }
                ErrorType.CONFIRM_PASSWORD ->
                    binding.confirmPasswordEditText.setError(it.errorMessage)
                ErrorType.NAME ->
                    binding.nameEditText.setError(it.errorMessage)
                ErrorType.SURNAME ->
                    binding.firstSurnameEditText.setError(it.errorMessage)
                ErrorType.BIRTHDATE ->
                    binding.birthEditText.setError(it.errorMessage)
                ErrorType.WRONG_BIRTHDATE ->
                    binding.birthEditText.setError(it.errorMessage)
                ErrorType.CONTACT_METHOD -> {
                    binding.emailEditText.setError(it.errorMessage)
                    binding.phoneEditText.setError(it.errorMessage)
                }
                ErrorType.WRONG_BIRTHDATE ->
                    binding.birthEditText.setError(it.errorMessage)
                ErrorType.API_PROBLEM ->
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
            }
        })

    }
}