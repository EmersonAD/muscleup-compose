package com.souzaemerson.muscleupgym.domain.repository

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import kotlinx.coroutines.flow.Flow

interface GetAllExercisesRepository {
    operator fun invoke(limit: Int): Flow<List<BodyPartEntity>>
}