package com.example.automatic_analytics_analyser.view

import androidx.lifecycle.ViewModel
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import javax.inject.Inject

class SettingsViewModel @Inject constructor(val repository: UserManagmentRepository) : ViewModel() {
}