package com.souzaemerson.muscleupgym.domain.di.usecase

import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division

interface RemoveAnnotationUseCase {
    suspend operator fun invoke(division: Division, annotation: Annotation)
}