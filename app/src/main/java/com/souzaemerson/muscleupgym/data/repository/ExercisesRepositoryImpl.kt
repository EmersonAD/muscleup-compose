package com.souzaemerson.muscleupgym.data.repository

import com.souzaemerson.muscleupgym.data.database.dao.ExercisesDao
import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import com.souzaemerson.muscleupgym.domain.repository.ExercisesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExercisesRepositoryImpl @Inject constructor(
    private val dao: ExercisesDao
) : ExercisesRepository {

    override fun getAllExercises(): Flow<List<BodyPartEntity>> = dao.getAllExercises()

    override suspend fun insertExercise(exercise: BodyPartEntity) = dao.insertExercise(exercise)

    override suspend fun exists(target: String): Boolean = dao.exists()
}
