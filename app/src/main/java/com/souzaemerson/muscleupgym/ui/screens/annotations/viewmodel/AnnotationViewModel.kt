package com.souzaemerson.muscleupgym.ui.screens.annotations.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.souzaemerson.muscleupgym.domain.di.repository.AnnotationsRepository
import com.souzaemerson.muscleupgym.domain.di.usecase.InsertAnnotationUseCase
import com.souzaemerson.muscleupgym.domain.di.usecase.RemoveAnnotationUseCase
import com.souzaemerson.muscleupgym.domain.di.usecase.UpdateAnnotationUseCase
import com.souzaemerson.muscleupgym.ui.screens.annotations.AnnotationEvent
import com.souzaemerson.muscleupgym.ui.screens.annotations.AnnotationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AnnotationViewModel @Inject constructor(
    private val annotationsRepository: AnnotationsRepository,
    private val updateAnnotation: UpdateAnnotationUseCase,
    private val insertAnnotation: InsertAnnotationUseCase,
    private val removeAnnotation: RemoveAnnotationUseCase
) : ViewModel() {

    var annotationState by mutableStateOf(AnnotationState())
        private set

    init {
        getAllDivisions()
    }

    fun onEvent(event: AnnotationEvent) {
        when (event) {
            is AnnotationEvent.CreateDivision -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        annotationsRepository.createDivision(event.division)
                    }
                    annotationState = annotationState.copy(
                        showBottomSheet = false
                    )
                }
            }

            is AnnotationEvent.UpdateDivision -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        annotationsRepository.updateDivision(event.division)
                    }
                }
            }

            is AnnotationEvent.DeleteDivision -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        annotationsRepository.delete(event.division)
                    }
                }
            }

            is AnnotationEvent.CreateAnnotation -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        insertAnnotation(event.division, event.annotation)
                    }
                    annotationState = annotationState.copy(
                        divisions = annotationsRepository.getAllDivisions().first(),
                        showCreateAnnotationAlert = false
                    )
                }
            }

            is AnnotationEvent.UpdateAnnotation -> {
                viewModelScope.launch {
                    updateAnnotation(event.division, event.oldAnnotation, event.newAnnotation)
                    annotationState = annotationState.copy(
                        divisions = annotationsRepository.getAllDivisions().first(),
                        showUpdateAnnotationAlert = false,
                        showDecisionAlert = false
                    )
                }
            }

            is AnnotationEvent.RemoveAnnotation -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        removeAnnotation(event.division, event.annotation)
                        annotationState = annotationState.copy(
                            divisions = annotationsRepository.getAllDivisions().first(),
                            showDecisionAlert = false
                        )
                    }
                }
            }
        }
    }

    fun openBottomSheet() {
        annotationState = annotationState.copy(
            showBottomSheet = true
        )
    }

    fun closeBottomSheet() {
        annotationState = annotationState.copy(
            showBottomSheet = false
        )
    }

    fun openCreateAnnotationAlert() {
        annotationState = annotationState.copy(
            showCreateAnnotationAlert = true
        )
    }

    fun closeCreateAnnotationAlert() {
        annotationState = annotationState.copy(
            showCreateAnnotationAlert = false
        )
    }

    fun openDecisionAlert() {
        annotationState = annotationState.copy(
            showDecisionAlert = true
        )
    }

    fun closeDecisionAlert() {
        annotationState = annotationState.copy(
            showDecisionAlert = false
        )
    }

    fun openUpdateAnnotationAlert() {
        annotationState = annotationState.copy(
            showUpdateAnnotationAlert = true
        )
    }

    fun closeUpdateAnnotationAlert() {
        annotationState = annotationState.copy(
            showUpdateAnnotationAlert = false
        )
    }

    private fun getAllDivisions() {
        viewModelScope.launch {
            annotationsRepository.getAllDivisions().collect { divisions ->
                annotationState = annotationState.copy(
                    divisions = divisions
                )
            }
        }
    }
}