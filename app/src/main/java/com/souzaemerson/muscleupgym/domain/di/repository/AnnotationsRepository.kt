package com.souzaemerson.muscleupgym.domain.di.repository

import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import kotlinx.coroutines.flow.Flow

interface AnnotationsRepository {
    fun getAllDivisions(): Flow<List<Division>>
    suspend fun createDivision(division: Division)
    suspend fun updateDivision(division: Division)
    suspend fun insertAnnotationIntoDivision(division: Division, annotation: Annotation)
    suspend fun delete(division: Division)
}