package com.souzaemerson.muscleupgym.domain.usecase

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import kotlinx.coroutines.flow.Flow

interface GetSelectedExercisesUseCase {
    operator fun invoke(bodyPart: String): Flow<List<BodyPartEntity>>
}