package com.souzaemerson.muscleupgym.data.source.remote

import com.souzaemerson.muscleupgym.data.source.remote.service.ExerciseService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RemoteDataSource<T> {
    fun fetchData(): Flow<T>
}

class ExerciseDataSource @Inject constructor(
    private val exerciseApi: ExerciseService,
) : RemoteDataSource<List<String>> {

    override fun fetchData(): Flow<List<String>> = flow {
        emit(exerciseApi.getBodyParts())
    }
}


