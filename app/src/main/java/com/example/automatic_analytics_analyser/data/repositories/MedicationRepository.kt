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
class MedicationRepository @Inject constructor(val api: ApiService, val context: Context) : AbstractRepository(){
    lateinit var token: String

    suspend fun getMedication(): List<Medication>? {
        token = getToken(context)
        val response: Response<List<Medication>> = api.getMedication(token)
        if (response.isSuccessful) {
            return response.body()!!
        } else
            return null
    }
}