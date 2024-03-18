package com.souzaemerson.muscleupgym.data.repository

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import com.souzaemerson.muscleupgym.data.source.remote.service.ExerciseService
import com.souzaemerson.muscleupgym.data.util.MockUtils
import com.souzaemerson.muscleupgym.domain.repository.ExercisesRepository
import com.souzaemerson.muscleupgym.domain.repository.GetAllExercisesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllExercisesRepositoryImpl @Inject constructor(
    private val apiService: ExerciseService,
    private val dao: ExercisesRepository,
    private val mock: MockUtils
) : GetAllExercisesRepository {

    override fun invoke(limit: Int): Flow<List<BodyPartEntity>> = flow {

        apiService.getBodyExercise(limit).forEach { exercise ->
            dao.insertExercise(exercise)
        }

        emit(apiService.getBodyExercise(limit))
    }.catch {

        mock.getMockFromAsset("Exercises.json").also {
            it?.forEach { exercise ->
                dao.insertExercise(exercise)
            }

            emit(it ?: emptyList())
        }
    }
}