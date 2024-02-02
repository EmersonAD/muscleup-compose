package com.souzaemerson.muscleupgym.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.souzaemerson.muscleupgym.ui.screens.exercises.HomeScreen

@Composable
fun MuscleUpNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = "home") {
            HomeScreen(
                onNavigateToExercise = {
                    navController.navigate("exercise")
                }
            )
        }
    }
}
