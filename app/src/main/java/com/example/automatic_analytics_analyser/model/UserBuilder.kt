package com.example.automatic_analytics_analyser.model

data class UserBuilder (
    var dni: String,
    var name: String,
    var firstSurname: String,
    var secondSurname: String,
    var password: String,
    var confirmPassword: String,
    var birthDate: String,
    var mail: String,
    var phoneNumber: String
)