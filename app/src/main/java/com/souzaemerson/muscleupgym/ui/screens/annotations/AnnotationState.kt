package com.souzaemerson.muscleupgym.ui.screens.annotations

import com.souzaemerson.muscleupgym.data.model.annotation.Division

data class AnnotationState(
    val divisions: List<Division> = emptyList(),
    val showBottomSheet: Boolean = false,
    val showCreateAnnotationAlert: Boolean = false,
    val showDecisionAlert: Boolean = false,
    val showUpdateAnnotationAlert: Boolean = false
)