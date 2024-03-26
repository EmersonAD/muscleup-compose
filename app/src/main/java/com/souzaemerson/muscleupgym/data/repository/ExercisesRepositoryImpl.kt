package com.souzaemerson.muscleupgym.data.repository

import com.souzaemerson.muscleupgym.data.database.dao.ExercisesDao
import com.souzaemerson.muscleupgym.data.model.body.Exercises
import com.souzaemerson.muscleupgym.domain.repository.ExercisesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import javax.inject.Inject

class ExercisesRepositoryImpl @Inject constructor(
    private val dao: ExercisesDao
) : ExercisesRepository {

    override fun getExercises(): Flow<Exercises> = dao.getAllExercises()

    override suspend fun insertExercise(exercise: Exercises) = dao.insertExercise(exercise)

    override suspend fun contains(): Boolean = dao.contains()

    override suspend fun isAValidExercises(): Boolean {
        if (contains()) {
            val currentTime = LocalDate.now()
            val lastTimeSavedOnDb = LocalDate.parse(getExercises().first().lastTimeSaved)

            return !currentTime.isAfter(lastTimeSavedOnDb)
        }

        return false
    }
}
