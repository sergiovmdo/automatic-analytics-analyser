package com.example.automatic_analytics_analyser.view.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automatic_analytics_analyser.data.repositories.ChatRepository
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.Analysis
import com.example.automatic_analytics_analyser.model.Chat
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor(val repository: ChatRepository) : ViewModel() {
    val chats: LiveData<List<Chat>>
        get() = _chats

    private val _chats = MutableLiveData<List<Chat>>()
    fun refreshChats() {
        viewModelScope.launch {
            val data = repository.getChats()
            _chats.postValue(data)
        }
    }
}