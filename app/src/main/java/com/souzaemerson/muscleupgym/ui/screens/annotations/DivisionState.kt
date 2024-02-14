package com.souzaemerson.muscleupgym.ui.screens.annotations

import com.souzaemerson.muscleupgym.data.model.annotation.Division

data class DivisionState(
    var addingDivision: Boolean? = false,
    val divisions: List<Division> = emptyList(),
    val deleting: Boolean = false
)