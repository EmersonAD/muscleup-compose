package com.souzaemerson.muscleupgym.data.repository

import com.souzaemerson.muscleupgym.data.preferences.BODY_PART
import com.souzaemerson.muscleupgym.data.preferences.DataStore
import com.souzaemerson.muscleupgym.data.source.remote.service.ExerciseService
import com.souzaemerson.muscleupgym.domain.repository.GetBodyPartListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GetBodyPartListRepositoryImpl @Inject constructor(
    private val exerciseService: ExerciseService,
    private val dataStore: DataStore
) : GetBodyPartListRepository {

    override fun invoke(): Flow<List<String>> = flow {
        dataStore.setList(BODY_PART, exerciseService.getBodyPartList())
        emit(exerciseService.getBodyPartList())
    }.catch {
        val mockList = listOf(
            "back",
            "cardio",
            "chest",
            "lower arms",
            "lower legs",
            "neck",
            "shoulders",
            "upper arms",
            "upper legs",
            "waist"
        )
        emit(mockList)
    }
}