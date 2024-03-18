package com.souzaemerson.muscleupgym.domain.usecase

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import kotlinx.coroutines.flow.Flow

interface GetAllExercisesUseCase {
    suspend operator fun invoke(limit: Int): Flow<List<BodyPartEntity>>
}