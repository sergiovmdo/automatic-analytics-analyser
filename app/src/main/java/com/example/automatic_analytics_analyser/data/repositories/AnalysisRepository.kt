package com.example.automatic_analytics_analyser.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import com.example.automatic_analytics_analyser.data.Resource
import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.model.Analysis
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalysisRepository @Inject constructor(val api: ApiService, val context: Context) :
    AbstractRepository() {
    lateinit var token: String

    suspend fun getAnalysis(): List<Analysis> {
        token = getToken(context)
        return api.getAnalysis(token)
    }
}