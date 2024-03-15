package com.souzaemerson.muscleupgym.data.usecase

import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.domain.repository.AnnotationsRepository
import com.souzaemerson.muscleupgym.domain.usecase.UpdateAnnotationUseCase
import javax.inject.Inject

class UpdateAnnotationUseCaseImpl @Inject constructor(
    private val repository: AnnotationsRepository
) : UpdateAnnotationUseCase {

    override suspend fun invoke(
        division: Division,
        oldAnnotation: Annotation,
        newAnnotation: Annotation
    ) {
        val updatedAnnotations = division.annotations.map {
            if (it == oldAnnotation) newAnnotation else it
        }

        val updatedDivision = division.copy(
            annotations = updatedAnnotations
        )

        repository.updateDivision(updatedDivision)
    }
}
