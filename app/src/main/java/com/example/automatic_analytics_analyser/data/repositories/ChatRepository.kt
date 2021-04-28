package com.example.automatic_analytics_analyser.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.model.*
import retrofit2.Response
import java.util.function.Consumer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(val api: ApiService, val context: Context) {
    private lateinit var preferences: SharedPreferences;
    var token: String
    var openChat: Long? = null
    var openChatCallback: ((NewMessage) -> Unit)? = null

    init {
        preferences =
            PreferenceManager.getDefaultSharedPreferences(context)

        token = preferences.getString("token", "")!!
    }

    suspend fun getChats(): List<Chat> {
        return api.getChats(token)
    }

    suspend fun createChat(chat: ChatBuilder): Chat? {
        val response: Response<Chat> = api.createChat(token, chat)
        if (response.isSuccessful) {
            return response.body()!!
        } else
            return null

    }

    suspend fun getChat(id: Long): ChatItem? {
        val response: Response<ChatItem> = api.getChat(token, id)
        if (response.isSuccessful) {
            return response.body()!!
        } else
            return null
    }

    suspend fun createMessage(message: SentMessage) {
        api.createMessage(token, message)
    }

    fun openChat(id: Long?, consumer: ((NewMessage) -> Unit)?) {
        openChat = id
        openChatCallback = consumer
    }

    fun clearOpenChat() {
        openChat(null, null)
    }

    fun handleMessage(message: NewMessage): Boolean {
        if (openChat != null && openChat!!.equals(message.chatId.toLong())) {
            openChatCallback?.let {
                it(message)
            }
            return true
        }
        return false
    }

}