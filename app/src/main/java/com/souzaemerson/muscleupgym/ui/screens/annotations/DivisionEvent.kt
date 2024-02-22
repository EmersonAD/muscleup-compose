package com.souzaemerson.muscleupgym.ui.screens.annotations

import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division

sealed class DivisionEvent {
    data class CreateDivision(val division: Division): DivisionEvent()
    data class UpdateDivision(val division: Division): DivisionEvent()
    data class DeleteDivision(val division: Division): DivisionEvent()
    data class InsertAnnotationIntoDivision(val division: Division, val annotation: Annotation): DivisionEvent()
    data class UpdateAnnotation(val division: Division, val oldAnnotation: Annotation, val newAnnotation: Annotation): DivisionEvent()
}