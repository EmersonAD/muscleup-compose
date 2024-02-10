package com.souzaemerson.muscleupgym.ui.screens.exercises.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.souzaemerson.muscleupgym.domain.di.repository.BodyPartsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bodyPartsRepository: BodyPartsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val state: StateFlow<HomeUiState> = _state

    init {
        getBodyParts()
    }

    private fun getBodyParts() {
        viewModelScope.launch {
            bodyPartsRepository.getBodyParts().catch {
                _state.value = HomeUiState.Error(it)
            }.collect { bodyParts ->
                _state.value = HomeUiState.Success(bodyParts)
            }
        }
    }

    sealed class HomeUiState {
        data object Loading : HomeUiState()
        data class Success(val bodyParts: List<String>) : HomeUiState()
        data class Error(val throwable: Throwable) : HomeUiState()
    }
}

