package com.souzaemerson.muscleupgym.data.usecase

import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.domain.repository.AnnotationsRepository
import com.souzaemerson.muscleupgym.domain.usecase.InsertAnnotationUseCase
import javax.inject.Inject

class InsertAnnotationUseCaseImpl @Inject constructor(
    private val repository: AnnotationsRepository
) : InsertAnnotationUseCase {

    override suspend fun invoke(division: Division, annotation: Annotation) {

        val updatedAnnotations = division.annotations.toMutableList().also {
            it.add(annotation)
        }

        val updatedDivision = division.copy(
            annotations = updatedAnnotations
        )

        repository.updateDivision(updatedDivision)
    }
}
