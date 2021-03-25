package com.example.automatic_analytics_analyser.model

enum class ErrorType(val errorMessage: String) {
    DNI("Este campo es obligatorio"),
    WRONG_DNI("El Dni introducido no es correcto"),
    PASSWORD("Este campo es obligatorio"),
    CONFIRM_PASSWORD("Este campo es obligatorio"),
    PASSWORD_MATCH("Las contraseñas no coinciden"),
    NAME("Este campo es obligatorio"),
    SURNAME("Este campo es obligatorio"),
    BIRTHDATE("Este campo es obligatorio"),
    WRONG_BIRTHDATE("Fecha inválida"),
    CONTACT_METHOD("Tienes que añadir al menos un método de contacto"),
    API_PROBLEM("No se ha podido crear el usuario")
}