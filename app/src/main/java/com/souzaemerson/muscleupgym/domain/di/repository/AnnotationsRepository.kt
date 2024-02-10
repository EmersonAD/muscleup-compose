package com.souzaemerson.muscleupgym.domain.di.repository

import com.souzaemerson.muscleupgym.core.Resource
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import kotlinx.coroutines.flow.Flow

interface AnnotationsRepository {
    suspend fun getAllAnnotations(): Flow<List<Division>>
    suspend fun createDivision(division: Division)
    suspend fun updateDivision(division: Division)
    suspend fun delete(division: Division)
}