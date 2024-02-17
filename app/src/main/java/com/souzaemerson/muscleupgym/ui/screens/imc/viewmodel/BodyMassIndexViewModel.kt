package com.souzaemerson.muscleupgym.ui.screens.imc.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.souzaemerson.muscleupgym.data.source.local.bmi.BmiDataSource
import com.souzaemerson.muscleupgym.ui.screens.imc.BmiEvent

class BodyMassIndexViewModel(
    private val bmiDataSource: BmiDataSource
) : ViewModel() {

    private val _bmi = mutableStateOf(0.0 to "")
    var bmi: MutableState<Pair<Double, String>> = _bmi

    fun onEvent(bmiEvent: BmiEvent) {
        if (bmiEvent is BmiEvent.CalculateBmi) with(bmiEvent) {
            _bmi.value = bmiDataSource.getBodyMassIndex(age, height, weight)
        }
    }
}