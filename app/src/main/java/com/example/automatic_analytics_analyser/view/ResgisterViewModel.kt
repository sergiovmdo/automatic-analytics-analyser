package com.example.automatic_analytics_analyser.view

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.automatic_analytics_analyser.model.UserBuilder
import javax.inject.Inject

class ResgisterViewModel @Inject constructor() : ViewModel() {
    val userProfile = MutableLiveData<UserBuilder>()

    init {
        userProfile.value =
                UserBuilder(
                        dni = it.dni,
                        userName = it.displayName,
                        birthDate = it.birthDate,
                        mail = it.email!!

                )
    }

    fun next(view: View) {
        val user = userProfile.value!!
        var showError = false

        if (user.dni.isNullOrEmpty()){
            showError = true
        }

        if (user.userName.isNullOrEmpty()){
            showError = true
        }

        if (user.firstSurname.isNullOrEmpty()){
            showError = true
        }

        if (user.secondSurname.isNullOrEmpty()){
            showError = true
        }

        if (user.birthDate.isNullOrEmpty()){
            showError = true
        }

        if (user.phoneNumber.isNullOrEmpty()){
            showError = true
        }

        if (user.mail.isNullOrEmpty()){
            showError = true
        }

    }
}