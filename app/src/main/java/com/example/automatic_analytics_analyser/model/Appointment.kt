package com.example.automatic_analytics_analyser.model

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class Appointment(
    val id: String,
    val date: Long,
    val disease: String,
    val location: String,
    val time: String
) {
    fun convertDate(): String {
        return getFormatedDate(date, "dd/MM/yyyy")
    }

    fun convertTime() : String{
        return getFormatedDate(date, "HH:mm")
    }

    private fun getFormatedDate(date : Long, pattern : String) : String {
        val df: DateFormat = SimpleDateFormat(
            pattern,
            Locale.getDefault()
        )
        return try {
            df.format(date)
        } catch (ignore: Exception) {
            ""
        }
    }

}