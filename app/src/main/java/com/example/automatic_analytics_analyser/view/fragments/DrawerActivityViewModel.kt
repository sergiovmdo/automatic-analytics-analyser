package com.example.automatic_analytics_analyser.view.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class DrawerActivityViewModel  @Inject constructor(val repository: UserManagmentRepository) : ViewModel() {
    val userProfile = MutableLiveData<User>()

    init {
        getUser()
    }

    fun getUser(){
        viewModelScope.launch {
            userProfile.postValue(repository.getUserProfile())
        }
    }
}