package com.souzaemerson.muscleupgym.data.repository

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import com.souzaemerson.muscleupgym.data.model.body.Exercises
import com.souzaemerson.muscleupgym.data.source.remote.service.ExerciseService
import com.souzaemerson.muscleupgym.data.util.MockUtils
import com.souzaemerson.muscleupgym.domain.repository.ExercisesRepository
import com.souzaemerson.muscleupgym.domain.repository.GetAllExercisesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject

class GetAllExercisesRepositoryImpl @Inject constructor(
    private val apiService: ExerciseService,
    private val dao: ExercisesRepository,
    private val mock: MockUtils
) : GetAllExercisesRepository {

    override fun invoke(limit: Int): Flow<List<BodyPartEntity>> = flow {

        apiService.getBodyExercise(limit).also { exercises ->
            val localExercises = Exercises(lastTimeSaved = LocalDate.now().toString(), exercises = exercises)
            dao.insertExercise(localExercises)
            emit(exercises)
        }
    }.catch {

        mock.getMockFromAsset("Exercises.json")?.also { exercises ->
            emit(exercises)
        }
    }
}