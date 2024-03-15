package com.souzaemerson.muscleupgym.domain.repository

import com.souzaemerson.muscleupgym.data.model.annotation.Division
import kotlinx.coroutines.flow.Flow

interface AnnotationsRepository {
    fun getAllDivisions(): Flow<List<Division>>
    suspend fun createDivision(division: Division)
    suspend fun updateDivision(division: Division)
    suspend fun delete(division: Division)
}