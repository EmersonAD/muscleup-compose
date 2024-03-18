package com.souzaemerson.muscleupgym.domain.repository

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {
    fun getAllExercises(): Flow<List<BodyPartEntity>>
    suspend fun insertExercise(exercise: BodyPartEntity)
    suspend fun exists(target: String = "abs"): Boolean
}