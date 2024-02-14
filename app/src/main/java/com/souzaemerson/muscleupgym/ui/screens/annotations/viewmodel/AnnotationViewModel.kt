package com.souzaemerson.muscleupgym.ui.screens.annotations.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.souzaemerson.muscleupgym.domain.di.repository.AnnotationsRepository
import com.souzaemerson.muscleupgym.ui.screens.annotations.DivisionEvent
import com.souzaemerson.muscleupgym.ui.screens.annotations.DivisionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnotationViewModel @Inject constructor(
    private val annotationsRepository: AnnotationsRepository
) : ViewModel() {

    private val _annotations = mutableStateOf(DivisionState())
    val annotations: State<DivisionState> = _annotations

    init {
        getAllDivisions()
    }

    fun onEvent(event: DivisionEvent) {
        when (event) {
            is DivisionEvent.AddDivision -> {
                _annotations.value = annotations.value.copy(
                    addingDivision = true
                )
                viewModelScope.launch {
                    annotationsRepository.createDivision(event.division)
                }
            }

            is DivisionEvent.CreateAnnotations -> {
                viewModelScope.launch {
                    annotationsRepository.updateDivision(event.division)
                }
            }

            is DivisionEvent.DeleteDivision -> {
                viewModelScope.launch {
                    annotationsRepository.delete(event.division)
                }
            }
        }
    }

    private fun getAllDivisions() {
        viewModelScope.launch {
            annotationsRepository.getAllAnnotations().collect { divisions ->
                _annotations.value = annotations.value.copy(
                    divisions = divisions
                )
            }
        }
    }

//    private fun getAllAnnotations() {
//        viewModelScope.launch {
//            annotationsRepository.getAllAnnotations().onEach {
//                _annotations.value = it
//            }.launchIn(viewModelScope)
//        }
//    }
//
//    fun createAnnotationDivision(division: Division) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                annotationsRepository.createDivision(division)
//            }
//        }
//    }
//
//    fun updateAnnotationDivision(division: Division) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                annotationsRepository.updateDivision(division)
//            }
//        }
//    }
//
//    fun deleteAnnotationDivision(division: Division) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                annotationsRepository.delete(division)
//            }
//        }
//    }
}