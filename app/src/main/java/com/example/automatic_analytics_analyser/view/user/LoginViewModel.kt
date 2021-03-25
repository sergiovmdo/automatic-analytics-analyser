package com.example.automatic_analytics_analyser.view.user

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.ErrorType
import com.example.automatic_analytics_analyser.model.LoginUser
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: UserManagmentRepository) : ViewModel() {
    val loginProfile = MutableLiveData<LoginUser>()

    private val _loginError = MutableLiveData<ErrorType>()
    val loginError: LiveData<ErrorType>
        get() {
            return _loginError
        }

    private val _loginCompleted = MutableLiveData<String>()
    val loginCompleted: LiveData<String>
        get() = _loginCompleted

    init {
        loginProfile.value =
            LoginUser(
                dni = "",
                password = ""
            )
    }

    fun login(view: View) {
        val user = loginProfile.value!!
        var showError = false
        var errorType = "Este campo es obligatorio"

        if (user.dni.isNullOrEmpty()){
            showError = true
        }

        if (user.password.isNullOrEmpty()){
            showError = true
        }

        if (!showError){
            val token = repository
        }
    }
}