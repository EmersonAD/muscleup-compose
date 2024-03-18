package com.souzaemerson.muscleupgym.data.usecase

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import com.souzaemerson.muscleupgym.domain.repository.GetSelectedExercisesRepository
import com.souzaemerson.muscleupgym.domain.usecase.GetSelectedExercisesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSelectedExercisesUseCaseImpl @Inject constructor(
    private val repository: GetSelectedExercisesRepository
) : GetSelectedExercisesUseCase {

    override fun invoke(bodyPart: String): Flow<List<BodyPartEntity>> {
        return repository().map { allExercises ->
            allExercises.filter { exercises ->
                exercises.bodyPart == bodyPart.lowercase()
            }
        }
    }
}