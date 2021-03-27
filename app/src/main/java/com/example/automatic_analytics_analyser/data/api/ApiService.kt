package com.example.automatic_analytics_analyser.data.api

import com.example.automatic_analytics_analyser.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("register")
    suspend fun newUser(@Body user: UserBuilder): TokenResponse

    @POST("login")
    suspend fun getUser(@Body user: LoginUser): TokenResponse

    @GET("user")
    suspend fun getUserProfile(@Header("Authorization") token: String): User

    @PUT("user")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body password: Password
    ): Response<Boolean>
}