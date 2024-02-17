package com.souzaemerson.muscleupgym.ui.screens.imc

sealed class BmiEvent {
    data class CalculateBmi(val age: Int, val height: Float, val weight: Double) : BmiEvent()
}