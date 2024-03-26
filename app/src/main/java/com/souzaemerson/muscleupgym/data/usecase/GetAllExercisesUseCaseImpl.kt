package com.souzaemerson.muscleupgym.data.usecase

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import com.souzaemerson.muscleupgym.domain.repository.ExercisesRepository
import com.souzaemerson.muscleupgym.domain.repository.GetAllExercisesRepository
import com.souzaemerson.muscleupgym.domain.usecase.GetAllExercisesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllExercisesUseCaseImpl @Inject constructor(
    private val repository: GetAllExercisesRepository,
    private val exercises: ExercisesRepository
) : GetAllExercisesUseCase {

    override suspend fun invoke(limit: Int): Flow<List<BodyPartEntity>> {
        return if (exercises.isAValidExercises()) {
            flow {
                exercises.getExercises().first().exercises
            }
        } else {
            repository.invoke(limit).map { exercises ->
                exercises.filter { exercise ->
                    exercise.gifUrl.isNotEmpty()
                }
            }
        }
    }
}