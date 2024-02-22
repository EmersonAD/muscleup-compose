package com.souzaemerson.muscleupgym.ui.screens.annotations

import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division

sealed class AnnotationEvent {
    data class CreateDivision(val division: Division): AnnotationEvent()
    data class UpdateDivision(val division: Division): AnnotationEvent()
    data class DeleteDivision(val division: Division): AnnotationEvent()
    data class CreateAnnotation(val division: Division, val annotation: Annotation): AnnotationEvent()
    data class UpdateAnnotation(val division: Division, val oldAnnotation: Annotation, val newAnnotation: Annotation): AnnotationEvent()
    data class RemoveAnnotation(val division: Division, val annotation: Annotation): AnnotationEvent()
}