package com.souzaemerson.muscleupgym.domain.di.repository

import kotlinx.coroutines.flow.Flow

interface BodyPartsRepository {
    suspend fun getBodyParts(): Flow<List<String>>
}