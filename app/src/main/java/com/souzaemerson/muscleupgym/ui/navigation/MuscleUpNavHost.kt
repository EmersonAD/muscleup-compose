package com.souzaemerson.muscleupgym.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem.Body
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem.Home
import com.souzaemerson.muscleupgym.ui.screens.annotations.AnnotationScreen
import com.souzaemerson.muscleupgym.ui.screens.annotations.viewmodel.AnnotationViewModel
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
        composable(route = Home.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val state = homeViewModel.state.collectAsState()
            HomeScreen(state = state.value)
        }
        composable(route = BottomNavigationItem.Annotation.route) {
            val annotationViewModel = hiltViewModel<AnnotationViewModel>()
            AnnotationScreen(viewModel = annotationViewModel)
        }
        composable(route = Body.route) {

        }
    }
}
