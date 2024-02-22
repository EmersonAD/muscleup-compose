package com.souzaemerson.muscleupgym.data.usecase

import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.domain.di.repository.AnnotationsRepository
import com.souzaemerson.muscleupgym.domain.di.usecase.InsertAnnotationUseCase
import com.souzaemerson.muscleupgym.domain.di.usecase.RemoveAnnotationUseCase
import com.souzaemerson.muscleupgym.domain.di.usecase.UpdateAnnotationUseCase
import javax.inject.Inject

class RemoveAnnotationUseCaseImpl @Inject constructor(
    private val repository: AnnotationsRepository
) : RemoveAnnotationUseCase {

    override suspend fun invoke(division: Division, annotation: Annotation) {

        val updatedAnnotations = division.annotations.toMutableList().apply {
            remove(annotation)
        }

        val updatedDivision = division.copy(
            annotations = updatedAnnotations
        )

        repository.updateDivision(updatedDivision)
    }
}
