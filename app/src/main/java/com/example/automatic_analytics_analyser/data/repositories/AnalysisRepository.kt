package com.example.automatic_analytics_analyser.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import com.example.automatic_analytics_analyser.data.Resource
import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.model.Analysis
import javax.inject.Inject

class AnalysisRepository @Inject constructor(val api: ApiService, val context: Context) {
    private lateinit var preferences: SharedPreferences;
    var token: String

    init {
        preferences =
            PreferenceManager.getDefaultSharedPreferences(context)

        token = preferences.getString("token", "")!!
    }

    suspend fun getAnalysis(): List<Analysis> {
        return api.getAnalysis(token)
    }
}