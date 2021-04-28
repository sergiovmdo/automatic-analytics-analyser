package com.example.automatic_analytics_analyser.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.model.Medication
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicationRepository @Inject constructor(val api: ApiService, val context: Context) {
    private lateinit var preferences: SharedPreferences;
    var token: String

    init {
        preferences =
            PreferenceManager.getDefaultSharedPreferences(context)

        token = preferences.getString("token", "")!!
    }

    suspend fun getMedication(): List<Medication>? {
        val response: Response<List<Medication>> = api.getMedication(token)
        if (response.isSuccessful) {
            return response.body()!!
        } else
            return null
    }
}