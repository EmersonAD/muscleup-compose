package com.souzaemerson.muscleupgym.data.repository

import com.souzaemerson.muscleupgym.data.source.remote.ExerciseDataSourceImpl
import com.souzaemerson.muscleupgym.domain.di.repository.BodyPartsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BodyPartsRepositoryImpl @Inject constructor(
    private val dataSource: ExerciseDataSourceImpl
) : BodyPartsRepository {

    override suspend fun getBodyParts(): Flow<List<String>> {
        return dataSource.fetchData().map {
                it.filter { bodyPart -> bodyPart.isNotEmpty() }
            }
    }
}