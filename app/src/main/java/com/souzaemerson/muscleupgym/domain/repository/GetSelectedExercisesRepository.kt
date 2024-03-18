package com.souzaemerson.muscleupgym.domain.repository

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import kotlinx.coroutines.flow.Flow

interface GetSelectedExercisesRepository {
    operator fun invoke(): Flow<List<BodyPartEntity>>
}