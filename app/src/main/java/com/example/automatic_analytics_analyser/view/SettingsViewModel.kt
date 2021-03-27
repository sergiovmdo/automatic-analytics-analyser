package com.example.automatic_analytics_analyser.view

import android.view.View
import androidx.lifecycle.*
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(val repository: UserManagmentRepository) : ViewModel() {
    val user = MutableLiveData<User>()
    val changePassword = MutableLiveData<Password>()

    private val _changePasswordError = MutableLiveData<ErrorType>()
    val changePasswordError: LiveData<ErrorType>
        get() {
            return _changePasswordError
        }


    init {
        viewModelScope.launch {
            val u = repository.getUserProfile()
            user.postValue(u)
        }

        changePassword.value =
            Password(
                currentPassword = "",
                password = "",
                confirmPassword = ""
            )
    }

    fun changePasswordAction(v: View) {
        val password = changePassword.value!!
        var showError = false

        if (password.password.isNullOrEmpty()) {
            showError = true
            _changePasswordError.value = ErrorType.PASSWORD
        }

        if (password.confirmPassword.isNullOrEmpty()) {
            showError = true
            _changePasswordError.value = ErrorType.CONFIRM_PASSWORD
        }

        if (!showError && !password.password.equals(password.confirmPassword)) {
            showError = true
            _changePasswordError.value = ErrorType.PASSWORD_MATCH
        }

        if (!showError) {
            viewModelScope.launch {
                val response = repository.changePassword(
                    Password(
                        password.currentPassword,
                        password.password,
                        password.currentPassword
                    )
                )
                if (!response) {
                    //TODO: Error treatment
                }
            }

        }
    }
}