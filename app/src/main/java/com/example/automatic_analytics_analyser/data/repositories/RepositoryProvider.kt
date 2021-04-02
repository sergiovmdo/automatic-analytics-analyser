package com.example.automatic_analytics_analyser.data.repositories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class RepositoryProvider @Inject constructor(
    val userManagmentRepository: UserManagmentRepository

)