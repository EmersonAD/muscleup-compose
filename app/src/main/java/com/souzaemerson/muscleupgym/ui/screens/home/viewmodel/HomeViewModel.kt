package com.souzaemerson.muscleupgym.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import com.souzaemerson.muscleupgym.domain.usecase.GetAllExercisesUseCase
import com.souzaemerson.muscleupgym.domain.usecase.GetBodyPartListUseCase
import com.souzaemerson.muscleupgym.ui.screens.home.util.HomeState
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
class HomeViewModel @Inject constructor(
    private val getBodyPartListUseCase: GetBodyPartListUseCase,
    private val getAllExercisesUseCase: GetAllExercisesUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    private var _exercises = MutableStateFlow<List<BodyPartEntity>>(emptyList())
    val exercise: StateFlow<List<BodyPartEntity>> = _exercises

    init {
        loadAllExercisesIntoDataBase()
        getBodyParts()
    }

    private fun loadAllExercisesIntoDataBase() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getAllExercisesUseCase(1400).catch {
                    Timber.e(it, it.message)
                }.collect { exercises ->
                    _exercises.value = exercises
                }
            }
        }
    }

    private fun getBodyParts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getBodyPartListUseCase().catch {
                    Timber.e(it, it.message)
                }.collect { bodyParts ->
                    _state.value = _state.value.copy(bodyParts = bodyParts)
                }
            }
        }
    }
}