package com.example.automatic_analytics_analyser.view.fragments.chat

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automatic_analytics_analyser.data.repositories.ChatRepository
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.*
import kotlinx.coroutines.launch
import java.util.*
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

    fun createMessage() {
        viewModelScope.launch {
            var messageContent = message.value!!.content
            repository.createMessage(SentMessage(_chat.value!!.id, messageContent))
            var m = Message(
                1L,
                messageContent,
                Calendar.getInstance().timeInMillis,
                chat.value!!.user
            )

            var localMessages = chat.value!!.messages.toMutableList()
            localMessages.add(m)
            _chat.value!!.messages = localMessages
            _chat.postValue(_chat.value)
        }
    }

    fun onChatOpen(id: Long) {
        repository.openChat(id) { _ ->
            getChat(id)
        }
    }

    fun onChatClose() {
        repository.clearOpenChat()
    }
}

