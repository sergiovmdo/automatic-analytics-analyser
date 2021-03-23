package com.example.automatic_analytics_analyser.data.repositories

import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.model.UserBuilder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManagmentRepository @Inject constructor(val api: ApiService){
    //Suspend is to make it asynchronous
    suspend fun createUser(user: UserBuilder) : Boolean {
        val response = api.newUser(user)
        return response.isSuccessful
    }
}