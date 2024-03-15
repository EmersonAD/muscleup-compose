package com.souzaemerson.muscleupgym.data.repository

import com.souzaemerson.muscleupgym.data.database.dao.AnnotationDao
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.domain.repository.AnnotationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnnotationsRepositoryImpl @Inject constructor(
    private val annotationsDao: AnnotationDao
) : AnnotationsRepository {

    override fun getAllDivisions(): Flow<List<Division>> = annotationsDao.getAllDivisions()

    override suspend fun createDivision(division: Division) =
        annotationsDao.insertDivision(division)

    override suspend fun updateDivision(division: Division) =
        annotationsDao.updateDivision(division)

    override suspend fun delete(division: Division) = annotationsDao.delete(division)
}
