package com.example.automatic_analytics_analyser.model

data class Appointment(
    val id: String,
    val date: String,
    val disease: String,
    val location: String,
    val time: String
)