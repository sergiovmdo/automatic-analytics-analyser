package com.example.automatic_analytics_analyser.data.api

import com.example.automatic_analytics_analyser.model.UserBuilder
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user")
    suspend fun newUser(@Body user: UserBuilder): Response<Unit>
}