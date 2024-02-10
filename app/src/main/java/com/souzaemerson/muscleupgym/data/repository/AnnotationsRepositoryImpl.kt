package com.souzaemerson.muscleupgym.data.repository

import com.souzaemerson.muscleupgym.core.Resource
import com.souzaemerson.muscleupgym.data.database.dao.AnnotationDao
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.domain.di.repository.AnnotationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnnotationsRepositoryImpl @Inject constructor(
    private val annotationsDao: AnnotationDao
) : AnnotationsRepository {

    override suspend fun createDivision(division: Division) {
        annotationsDao.insertDivision(division)
    }

    override suspend fun updateDivision(division: Division) {
        annotationsDao.addAnnotationIntoDivision(division)
    }

    override suspend fun delete(division: Division) {
        annotationsDao.delete(division)
    }

    override suspend fun getAllAnnotations(): Flow<List<Division>> =
        annotationsDao.getAllDivisions()

}
