package com.example.automatic_analytics_analyser.view.user

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.automatic_analytics_analyser.model.UserBuilder
import java.lang.Exception
import androidx.lifecycle.viewModelScope
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.ErrorType
import com.example.automatic_analytics_analyser.model.FCMToken
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ResgisterViewModel @Inject constructor(val repository: UserManagmentRepository) :
    ViewModel() {
    val userProfile = MutableLiveData<UserBuilder>()

    private val _registerError = MutableLiveData<ErrorType>()
    val registerError: LiveData<ErrorType>
        get() {
            return _registerError
        }

    private val _registerCompleted = MutableLiveData<String>()
    val registerCompleted: LiveData<String>
        get() = _registerCompleted



    init {
        userProfile.value =
            UserBuilder(
                dni = "333",
                password = "pepe",
                confirmPassword = "pepe",
                name = "pepe",
                firstSurname = "pepe",
                secondSurname = "pepe",
                birthDate = "06/09/1997",
                mail = "pepe@gmail.com",
                phoneNumber = "678652014"
            )
    }

    fun register(fcmToken: FCMToken) {
        val user = userProfile.value!!
        var showError = false
        var errorType = ""

        if (user.dni.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
            _registerError.value = ErrorType.DNI
        } else {
            //TODO: Check if DNI is correct
        }

        if (user.password.isNullOrEmpty()) {
            showError = true;
            errorType = "Este campo es obligatorio"
            _registerError.value = ErrorType.PASSWORD
        }

        if (user.confirmPassword.isNullOrEmpty()) {
            showError = true;
            errorType = "Este campo es obligatorio"
            _registerError.value = ErrorType.CONFIRM_PASSWORD
        } else if (!user.password.equals(user.confirmPassword)) {
            showError = true
            errorType = "Las contraseñas no coinciden"
            _registerError.value = ErrorType.PASSWORD_MATCH
        }

        if (user.name.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
            _registerError.value = ErrorType.NAME
        }

        if (user.firstSurname.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
            _registerError.value = ErrorType.SURNAME
        }

        if (user.birthDate.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
            _registerError.value = ErrorType.BIRTHDATE
        } else {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val today = Calendar.getInstance()
            try {
                val userBirthDate = dateFormat.parse(user.birthDate)
                if (userBirthDate > today.time) {
                    showError = true
                    errorType = "Fecha inválida"
                    _registerError.value = ErrorType.WRONG_BIRTHDATE
                }
            } catch (e: Exception) {
                showError = true
                errorType = "Fecha inválida"
                _registerError.value = ErrorType.WRONG_BIRTHDATE
            }
        }

        if (user.mail.isNullOrEmpty() && user.phoneNumber.isNullOrEmpty()) {
            showError = true
            errorType = "Tienes que añadir al menos un método de contacto"
            _registerError.value = ErrorType.CONTACT_METHOD
        }

        if (!showError) {
            //Save the user into DB and go back to login activity
            viewModelScope.launch {
                val token = repository.createUser(user)
                repository.insertFCMToken(fcmToken)
                if (token.isNullOrEmpty()) {
                    _registerError.value =
                        ErrorType.API_PROBLEM
                } else {
                    _registerCompleted.value = token
                }
            }
        }

    }
}