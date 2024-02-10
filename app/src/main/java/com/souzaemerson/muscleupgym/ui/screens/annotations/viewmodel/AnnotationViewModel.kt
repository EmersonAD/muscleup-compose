package com.souzaemerson.muscleupgym.ui.screens.annotations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.domain.di.repository.AnnotationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AnnotationViewModel @Inject constructor(
    private val annotationsRepository: AnnotationsRepository
) : ViewModel() {

    private val _annotations: MutableStateFlow<List<Division>> = MutableStateFlow(emptyList())
    val annotations: StateFlow<List<Division>> = _annotations

    init {
        getAllAnnotations()
    }

    private fun getAllAnnotations() {
        viewModelScope.launch {
            annotationsRepository.getAllAnnotations().onEach {
                _annotations.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun createAnnotationDivision(division: Division) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                annotationsRepository.createDivision(division)
            }
        }
    }

    fun updateAnnotationDivision(division: Division) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                annotationsRepository.updateDivision(division)
            }
        }
    }

    fun deleteAnnotationDivision(division: Division) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                annotationsRepository.delete(division)
            }
        }
    }
}