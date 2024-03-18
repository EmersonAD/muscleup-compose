package com.souzaemerson.muscleupgym.data.usecase

import com.souzaemerson.muscleupgym.data.preferences.BODY_PART
import com.souzaemerson.muscleupgym.data.preferences.DataStore
import com.souzaemerson.muscleupgym.domain.repository.GetBodyPartListRepository
import com.souzaemerson.muscleupgym.domain.usecase.GetBodyPartListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBodyPartListUseCaseImpl @Inject constructor(
    private val getBodyPartList: GetBodyPartListRepository,
    private val dataStore: DataStore
) : GetBodyPartListUseCase {

    override suspend fun invoke(): Flow<List<String>> = if (dataStore.exists(BODY_PART).first()) {
        dataStore.getList(BODY_PART)
    } else {
        getBodyPartList().map { bodyParts ->
            bodyParts.filter { bodyPart ->
                bodyPart.isNotBlank()
            }
        }
    }
}