package com.example.automatic_analytics_analyser.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.model.*
import retrofit2.Response
import javax.inject.Inject

class ChatRepository @Inject constructor(val api: ApiService, val context: Context) {
    private lateinit var preferences: SharedPreferences;
    var token: String

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
}