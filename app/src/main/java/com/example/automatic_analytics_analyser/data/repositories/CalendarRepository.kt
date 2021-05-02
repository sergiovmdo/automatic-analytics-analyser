package com.example.automatic_analytics_analyser.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.model.Appointment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalendarRepository @Inject constructor(val api: ApiService, val context: Context) :
    AbstractRepository() {
    lateinit var token: String

    suspend fun getAppointments(): List<Appointment> {
        token = getToken(context)
        return api.getAppointments(token)
    }
}