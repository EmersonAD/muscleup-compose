package com.souzaemerson.muscleupgym.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetBodyPartListUseCase {
    suspend operator fun invoke(): Flow<List<String>>
}