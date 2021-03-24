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
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ResgisterViewModel @Inject constructor(val repository: UserManagmentRepository) :
    ViewModel() {
    val userProfile = MutableLiveData<UserBuilder>()

    private val _registerError = MutableLiveData<Pair<ErrorType, String>>()
    val registerError: LiveData<Pair<ErrorType, String>>
        get() {
            return _registerError
        }

    private val _registerCompleted = MutableLiveData<Boolean>()
    val registerCompleted: LiveData<Boolean>
        get() = _registerCompleted

    enum class ErrorType {
        DNI,
        WRONG_DNI,
        PASSWORD,
        CONFIRM_PASSWORD,
        PASSWORD_MATCH,
        NAME,
        SURNAME,
        BIRTHDATE,
        WRONG_BIRTHDATE,
        CONTACT_METHOD,
        API_PROBLEM
    }

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
                phoneNumber = ""
            )
    }

    fun register(view: View) {
        val user = userProfile.value!!
        var showError = false
        var errorType = ""

        if (user.dni.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
            _registerError.value = Pair(ErrorType.DNI, errorType)
        } else {
            //TODO: Check if DNI is correct
        }

        if (user.password.isNullOrEmpty()) {
            showError = true;
            errorType = "Este campo es obligatorio"
            _registerError.value = Pair(ErrorType.PASSWORD, errorType)
        }

        if (user.confirmPassword.isNullOrEmpty()) {
            showError = true;
            errorType = "Este campo es obligatorio"
            _registerError.value = Pair(ErrorType.CONFIRM_PASSWORD, errorType)
        } else if (!user.password.equals(user.confirmPassword)) {
            showError = true
            errorType = "Las contraseñas no coinciden"
            _registerError.value = Pair(ErrorType.PASSWORD_MATCH, errorType)
        }

        if (user.name.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
            _registerError.value = Pair(ErrorType.NAME, errorType)
        }

        if (user.firstSurname.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
            _registerError.value = Pair(ErrorType.SURNAME, errorType)
        }

        if (user.birthDate.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
            _registerError.value = Pair(ErrorType.BIRTHDATE, errorType)
        } else {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val today = Calendar.getInstance()
            try {
                val userBirthDate = dateFormat.parse(user.birthDate)
                if (userBirthDate > today.time) {
                    showError = true
                    errorType = "Fecha inválida"
                    _registerError.value = Pair(ErrorType.WRONG_BIRTHDATE, errorType)
                }
            } catch (e: Exception) {
                showError = true
                errorType = "Fecha inválida"
                _registerError.value = Pair(ErrorType.WRONG_BIRTHDATE, errorType)
            }
        }

        if (user.mail.isNullOrEmpty() && user.phoneNumber.isNullOrEmpty()) {
            showError = true
            errorType = "Tienes que añadir al menos un método de contacto"
            _registerError.value = Pair(ErrorType.CONTACT_METHOD, errorType)
        }

        if (!showError) {
            //Save the user into DB and go back to login activity
            viewModelScope.launch {
                val token = repository.createUser(user)
                if (token.isNullOrEmpty()) {
                    _registerError.value =
                        Pair(ErrorType.API_PROBLEM, "No se ha podido crear el usuario")
                } else {

                }
            }
        }

    }
}