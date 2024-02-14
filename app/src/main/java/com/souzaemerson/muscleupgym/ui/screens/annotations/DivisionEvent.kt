package com.souzaemerson.muscleupgym.ui.screens.annotations

import com.souzaemerson.muscleupgym.data.model.annotation.Division

sealed class DivisionEvent {
    data class AddDivision(val division: Division): DivisionEvent()
    data class CreateAnnotations(val division: Division): DivisionEvent()
    data class DeleteDivision(val division: Division): DivisionEvent()
}