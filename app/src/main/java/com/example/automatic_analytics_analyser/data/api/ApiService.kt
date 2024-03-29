package com.example.automatic_analytics_analyser.data.api

import com.example.automatic_analytics_analyser.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("register")
    suspend fun newUser(@Body user: UserBuilder): Response<TokenResponse>

    @POST("login")
    suspend fun getUser(@Body user: LoginUser): Response<TokenResponse>

    @GET("user")
    suspend fun getUserProfile(@Header("Authorization") token: String): User

    @PUT("user")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body password: Password
    ): Response<Unit>

    @GET("analysis")
    suspend fun getAnalysis(@Header("Authorization") token: String): List<Analysis>

    @PUT("user/fcmtoken")
    suspend fun insertFCMToken(
        @Header("Authorization") token: String,
        @Body fcmToken: FCMToken
    ): Response<Unit>

    @GET("calendar")
    suspend fun getAppointments(@Header("Authorization") token: String): List<Appointment>

    @GET("chat")
    suspend fun getChats(@Header("Authorization") token: String): List<Chat>

    @POST("chat")
    suspend fun createChat(
        @Header("Authorization") token: String,
        @Body chatBuilder: ChatBuilder
    ): Response<Chat>

    @GET("chat/{id}")
    suspend fun getChat(
        @Header("Authorization") token: String,
        @Path("id") id: Long
    ): Response<ChatItem>

    @POST("chat/message")
    suspend fun createMessage(
        @Header("Authorization") token: String,
        @Body message: SentMessage
    ): Response<Unit>

    @GET("medication")
    suspend fun getMedication(@Header("Authorization") token: String): Response<List<Medication>>

    @PUT("user/language")
    suspend fun changeLanguage(
        @Header("Authorization") token: String,
        @Body language: Language
    ): Response<Unit>

}