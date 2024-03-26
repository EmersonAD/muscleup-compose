package com.souzaemerson.muscleupgym.domain.repository

import com.souzaemerson.muscleupgym.data.model.body.Exercises
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {
    fun getExercises(): Flow<Exercises>
    suspend fun insertExercise(exercise: Exercises)
    suspend fun contains(): Boolean
    suspend fun isAValidExercises(): Boolean
}