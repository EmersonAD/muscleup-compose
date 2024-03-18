package com.souzaemerson.muscleupgym.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface MuscleUpDestination {
    val route: String
}

object Exercises : MuscleUpDestination {
    override val route: String = "exercises"
    const val exerciseTypeArg = "bodyPart"
    val routeWithArgs = "${route}/{${exerciseTypeArg}}"
    val arguments = listOf(navArgument(exerciseTypeArg) {
        type = NavType.StringType
    })
}

