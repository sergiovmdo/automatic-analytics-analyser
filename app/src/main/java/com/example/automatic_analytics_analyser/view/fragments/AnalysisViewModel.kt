package com.example.automatic_analytics_analyser.view.fragments

import androidx.lifecycle.*
import com.example.automatic_analytics_analyser.data.Resource
import com.example.automatic_analytics_analyser.data.repositories.AnalysisRepository
import com.example.automatic_analytics_analyser.model.Analysis
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnalysisViewModel @Inject constructor(private var repository: AnalysisRepository) :
    ViewModel() {
    val analysis: LiveData<List<Analysis>>
        get() = _analysis

    private val _analysis = MutableLiveData<List<Analysis>>()

    fun refreshAnalysis() {
        viewModelScope.launch {
            val data = repository.getAnalysis()
            _analysis.postValue(data)
        }
    }
}