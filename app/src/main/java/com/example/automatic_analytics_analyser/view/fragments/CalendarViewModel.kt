package com.example.automatic_analytics_analyser.view.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automatic_analytics_analyser.data.repositories.AnalysisRepository
import com.example.automatic_analytics_analyser.data.repositories.CalendarRepository
import com.example.automatic_analytics_analyser.model.Analysis
import com.example.automatic_analytics_analyser.model.Appointment
import kotlinx.coroutines.launch
import javax.inject.Inject

class CalendarViewModel @Inject constructor(private var repository: CalendarRepository) :
    ViewModel(){

    val appointments: LiveData<List<Appointment>>
        get() = _appointments

    private val _appointments = MutableLiveData<List<Appointment>>()

    fun refreshCalendar(){
        viewModelScope.launch {
            val data = repository.getAppointments()
            _appointments.postValue(data)
        }
    }
}