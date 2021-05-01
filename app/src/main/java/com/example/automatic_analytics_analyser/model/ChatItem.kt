package com.example.automatic_analytics_analyser.model

data class ChatItem(
    val id : Long,
    val nursingSpeciality : String,
    var messages : List<Message>,
    val createdDate : Long,
    val user : SimplifiedUser
)