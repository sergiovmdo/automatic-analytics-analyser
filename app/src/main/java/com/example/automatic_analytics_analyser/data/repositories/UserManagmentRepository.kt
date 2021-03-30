package com.example.automatic_analytics_analyser.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.model.*
import com.example.automatic_analytics_analyser.view.MainActivity
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManagmentRepository @Inject constructor(val api: ApiService, val context: Context) {
    private lateinit var preferences: SharedPreferences;
    var token: String

    init {
        preferences =
            PreferenceManager.getDefaultSharedPreferences(context)

        token = preferences.getString("token", "")!!
    }

    //Suspend is to make it asynchronous
    suspend fun createUser(user: UserBuilder): String {
        val response = api.newUser(user)
        token = response.token
        preferences.edit().putString("token", response.token).apply()
        preferences.edit().putBoolean("logged", true).apply()
        return response.token
    }

    suspend fun getUser(user: LoginUser): String {
        val response = api.getUser(user)
        token = response.token
        preferences.edit().putString("token", response.token).apply()
        preferences.edit().putBoolean("logged", true).apply()
        return response.token
    }

    suspend fun getUserProfile(): User {
        val response = api.getUserProfile(token)
        return response
    }

    suspend fun changePassword(password: Password): Boolean {
        return api.changePassword(token, password).isSuccessful
    }

}