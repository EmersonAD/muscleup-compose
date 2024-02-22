package com.souzaemerson.muscleupgym.domain.di.usecase

import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division

interface UpdateAnnotationUseCase {
    suspend operator fun invoke(division: Division, oldAnnotation: Annotation, newAnnotation: Annotation)
}