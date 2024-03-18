package com.souzaemerson.muscleupgym.domain.repository

import kotlinx.coroutines.flow.Flow

interface GetBodyPartListRepository {
    operator fun invoke(): Flow<List<String>>
}