package com.example.automatic_analytics_analyser.view.fragments.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automatic_analytics_analyser.data.repositories.ChatRepository
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.Analysis
import com.example.automatic_analytics_analyser.model.Chat
import com.example.automatic_analytics_analyser.model.ChatBuilder
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor(val repository: ChatRepository) : ViewModel() {
    val chats: LiveData<List<Chat>>
        get() = _chats

    val createdChat : LiveData<Chat?>
        get() = _chat

    private val _chat = MutableLiveData<Chat?>(null)

    private val _chats = MutableLiveData<List<Chat>>()
    fun refreshChats() {
        viewModelScope.launch {
            val data = repository.getChats()
            _chats.postValue(data)
        }
    }

    fun createMessage(messageContent: String?, speciality: String?) {
        viewModelScope.launch {
            val chat = repository.createChat(ChatBuilder(speciality!!, messageContent!!))
            chat?.let {
                _chat.postValue(it)
            }
        }
    }
}