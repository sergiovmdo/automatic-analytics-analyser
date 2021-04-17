package com.example.automatic_analytics_analyser.view.fragments.chat

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automatic_analytics_analyser.data.repositories.ChatRepository
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.ChatItem
import com.example.automatic_analytics_analyser.model.Message
import com.example.automatic_analytics_analyser.model.SentMessage
import com.example.automatic_analytics_analyser.model.SimplifiedUser
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatItemViewModel @Inject constructor(val repository: ChatRepository) : ViewModel() {
    val chat: LiveData<ChatItem>
        get() = _chat

    val _chat = MutableLiveData<ChatItem>()

    val message = MutableLiveData<SentMessage>()

    init {
        message.value = SentMessage(
            content = "",
            chatId = 0
        )
    }

    fun getChat(id: Long) {
        viewModelScope.launch {
            val data = repository.getChat(id)
            _chat.postValue(data)
        }
    }

    fun createMessage(view: View) {
        viewModelScope.launch {
            repository.createMessage(SentMessage(_chat.value!!.id, message.value!!.content))
        }
    }
}

