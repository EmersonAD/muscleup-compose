package com.souzaemerson.muscleupgym.ui.screens.home.state

data class HomeState(
    val bodyParts: List<String> = emptyList(),
    val openExercisesScreen: Boolean = false
)