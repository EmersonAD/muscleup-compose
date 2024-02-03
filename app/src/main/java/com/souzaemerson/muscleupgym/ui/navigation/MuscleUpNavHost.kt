package com.souzaemerson.muscleupgym.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.souzaemerson.muscleupgym.ui.screens.exercises.HomeScreen
import com.souzaemerson.muscleupgym.ui.screens.exercises.viewmodel.HomeViewModel

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
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val state = homeViewModel.state.collectAsState()
            HomeScreen(state = state.value)
        }
    }
}
