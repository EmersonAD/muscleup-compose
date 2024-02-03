package com.souzaemerson.muscleupgym.data.source.remote

import com.souzaemerson.muscleupgym.data.source.remote.service.ExerciseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ExerciseDataSource {
    fun fetchData(): Flow<List<String>>
}

class ExerciseDataSourceImpl @Inject constructor(
    private val exerciseApi: ExerciseService,
) : ExerciseDataSource {
    override fun fetchData(): Flow<List<String>> = flow {
        emit(exerciseApi.getBodyParts())
    }
}


