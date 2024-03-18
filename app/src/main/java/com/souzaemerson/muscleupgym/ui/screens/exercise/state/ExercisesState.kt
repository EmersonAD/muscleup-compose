package com.souzaemerson.muscleupgym.ui.screens.exercise.state

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity

data class ExercisesState(
    var isLoading: Boolean = false,
    var exercises: List<BodyPartEntity> = emptyList()
)