package com.example.automatic_analytics_analyser.model

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class Analysis(
    val id: String,
    val date: Long,
    val disease: String,
    val analysisData: String,
    val content: String,
    val modified: Boolean

) {
    fun convertDate(): String {
        return getTime(date, "dd/MM/yyyy")
    }

    private fun getTime(date: Long, pattern: String): String {
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