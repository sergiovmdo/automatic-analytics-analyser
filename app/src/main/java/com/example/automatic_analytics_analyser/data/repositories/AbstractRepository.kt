package com.example.automatic_analytics_analyser.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

abstract class AbstractRepository {
    private lateinit var preferences: SharedPreferences;
    fun getToken(context: Context): String {
        preferences =
            PreferenceManager.getDefaultSharedPreferences(context)

        return preferences.getString("token", "")!!
    }
}