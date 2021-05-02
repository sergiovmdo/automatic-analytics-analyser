package com.example.automatic_analytics_analyser.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.model.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import java.util.concurrent.CountDownLatch
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
    suspend fun createUser(user: UserBuilder): Boolean {
        val response = api.newUser(user)
        if (response.isSuccessful) {
            token = response.body()!!.token
            preferences.edit().putString("token", token).apply()
            preferences.edit().putBoolean("logged", true).apply()
            return true
        }

        return false
    }

    suspend fun getUser(user: LoginUser): Boolean {
        val response = api.getUser(user)
        if (response.isSuccessful) {
            token = response.body()!!.token
            preferences.edit().putString("token", token).apply()
            preferences.edit().putBoolean("logged", true).apply()
            return true
        }

        return false
    }

    suspend fun getUserProfile(): User {
        val response = api.getUserProfile(token)
        return response
    }

    suspend fun changePassword(password: Password): Boolean {
        return api.changePassword(token, password).isSuccessful
    }

    suspend fun insertFCMToken(fcmToken: FCMToken) {
        api.insertFCMToken(token, fcmToken)
    }

    suspend fun changeLanguage(language: String): Boolean {
        return api.changeLanguage(token, Language(language)).isSuccessful
    }

}