package com.example.automatic_analytics_analyser.data.api

import com.example.automatic_analytics_analyser.model.TokenResponse
import com.example.automatic_analytics_analyser.model.UserBuilder
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun newUser(@Body user: UserBuilder): TokenResponse

    @GET("login")
    suspend fun getUser(): Response<UserBuilder>
}