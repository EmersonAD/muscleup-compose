package com.souzaemerson.muscleupgym.ui.screens.exercise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.souzaemerson.muscleupgym.domain.usecase.GetSelectedExercisesUseCase
import com.souzaemerson.muscleupgym.ui.screens.exercise.state.ExercisesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val getSelectedExercisesUseCase: GetSelectedExercisesUseCase
) : ViewModel() {

    private val _exercises = MutableStateFlow(
        ExercisesState(
            isLoading = true
        )
    )
    val exercise: StateFlow<ExercisesState> = _exercises

    fun getSelectedExercises(bodyPart: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getSelectedExercisesUseCase(bodyPart).catch {
                    Timber.e(it, it.message)
                }.collect { exercises ->

                    _exercises.value = _exercises.value.copy(
                        isLoading = false,
                        exercises = exercises
                    )
                }
            }
        }
    }
}