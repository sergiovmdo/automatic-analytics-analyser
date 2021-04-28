package com.example.automatic_analytics_analyser.model

data class Message(
    val id: Long,
    val content: String,
    val createdDate: Long,
    val user: SimplifiedUser
)