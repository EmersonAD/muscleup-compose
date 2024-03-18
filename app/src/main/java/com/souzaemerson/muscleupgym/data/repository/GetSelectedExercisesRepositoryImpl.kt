package com.souzaemerson.muscleupgym.data.repository

import com.souzaemerson.muscleupgym.data.database.dao.ExercisesDao
import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import com.souzaemerson.muscleupgym.domain.repository.GetSelectedExercisesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSelectedExercisesRepositoryImpl @Inject constructor(
    private val exercisesDao: ExercisesDao
) : GetSelectedExercisesRepository {

    override fun invoke(): Flow<List<BodyPartEntity>> = flow {
        if (exercisesDao.exists()) {
            exercisesDao.getAllExercises().collect { exercises ->
                emit(exercises)
            }
        } else {
            emit(emptyList())
        }
    }
}