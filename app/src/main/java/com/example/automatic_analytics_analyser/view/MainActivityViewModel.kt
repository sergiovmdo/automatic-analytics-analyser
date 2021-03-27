package com.example.automatic_analytics_analyser.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(val repository: UserManagmentRepository) : ViewModel() {
    val user = MutableLiveData<User>()
    init {
        viewModelScope.launch {
            val u = repository.getUserProfile()
            user.postValue(u)
        }
    }
}