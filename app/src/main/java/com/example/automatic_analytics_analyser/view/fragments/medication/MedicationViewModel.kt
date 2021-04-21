package com.example.automatic_analytics_analyser.view.fragments.medication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automatic_analytics_analyser.data.repositories.CalendarRepository
import com.example.automatic_analytics_analyser.data.repositories.MedicationRepository
import com.example.automatic_analytics_analyser.model.Medication
import kotlinx.coroutines.launch
import javax.inject.Inject

class MedicationViewModel @Inject constructor(private var repository: MedicationRepository) :
    ViewModel() {

    val medication: LiveData<List<Medication>>
        get() = _medication

    private val _medication = MutableLiveData<List<Medication>>()

    fun refreshMedication(){
        viewModelScope.launch {
            val data = repository.getMedication()
            _medication.postValue(data)
        }
    }

}