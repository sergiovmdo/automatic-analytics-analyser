package com.example.automatic_analytics_analyser.view.user

import android.view.View
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

    private val _registerError = MutableLiveData<ErrorType>()
    val registerError: LiveData<ErrorType>
        get() {
            return _registerError
        }

    enum class ErrorType {
        DNI,
        WRONG_DNI,
        PASSWORD,
        PASSWORD_MATCH,
        NAME,
        SURNAME,
        BIRTHDATE,
        WRONG_BIRTHDATE,
        CONTACT_METHOD,

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
        }

        if (user.password.isNullOrEmpty()) {
            showError = true;
            errorType = "Este campo es obligatorio"
        }

        if (user.confirmPassword.isNullOrEmpty()) {
            showError = true;
            errorType = "Este campo es obligatorio"
        } else if (!user.password.equals(user.confirmPassword)) {
            showError = true
            errorType = "Las contraseñas no coinciden"
        }

        if (user.name.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
        }

        if (user.firstSurname.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
        }

        if (user.secondSurname.isNullOrEmpty()) {
            showError = true
        }

        if (user.birthDate.isNullOrEmpty()) {
            showError = true
            errorType = "Este campo es obligatorio"
        } else {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val today = Calendar.getInstance()
            try {
                val userBirthDate = dateFormat.parse(user.birthDate)
                if (userBirthDate > today.time) {
                    showError = true
                    errorType = "Fecha inválida"
                }
            } catch (e: Exception) {
                showError = true
                errorType = "Fecha inválida"
            }
        }

        if (user.mail.isNullOrEmpty() && user.phoneNumber.isNullOrEmpty()) {
            showError = true
            errorType = "Tienes que añadir al menos un método de contacto"
        }

        if (!showError) {
            //Save the user into DB and go back to login activity
            viewModelScope.launch {
                val inserted = repository.createUser(user)
                //TODO: Comprobar si se inserta o no y hacer cositas frescas
            }
        }

    }
}