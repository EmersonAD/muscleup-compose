package com.souzaemerson.muscleupgym.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem.Body
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem.Home
import com.souzaemerson.muscleupgym.ui.extensions.slideIntoTransition
import com.souzaemerson.muscleupgym.ui.extensions.slideOutTransition
import com.souzaemerson.muscleupgym.ui.screens.exercise.ExercisesScreen
import com.souzaemerson.muscleupgym.ui.screens.annotations.AnnotationScreen
import com.souzaemerson.muscleupgym.ui.screens.annotations.viewmodel.AnnotationViewModel
import com.souzaemerson.muscleupgym.ui.screens.exercise.viewmodel.ExercisesViewModel
import com.souzaemerson.muscleupgym.ui.screens.home.HomeScreen
import com.souzaemerson.muscleupgym.ui.screens.home.viewmodel.HomeViewModel
import com.souzaemerson.muscleupgym.ui.screens.imc.BodyMassIndexScreen
import com.souzaemerson.muscleupgym.ui.screens.imc.viewmodel.BodyMassIndexViewModel

@Composable
fun MuscleUpNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    val currentRoute =
        remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    val previousRoute = remember { mutableStateOf(currentRoute.value) }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            previousRoute.value = currentRoute.value
            currentRoute.value = destination.route
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Home.route, enterTransition = {
            slideIntoTransition(direction = AnimatedContentTransitionScope.SlideDirection.End)
        }, exitTransition = {
            slideOutTransition(direction = AnimatedContentTransitionScope.SlideDirection.Start)
        }) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel = homeViewModel, navHost = navController)
        }

        composable(route = BottomNavigationItem.Annotation.route, enterTransition = {
            slideIntoTransition(
                direction = if (previousRoute.value == "home") {
                    AnimatedContentTransitionScope.SlideDirection.Start
                } else {
                    AnimatedContentTransitionScope.SlideDirection.End
                }
            )
        }, exitTransition = {
            slideOutTransition(direction = AnimatedContentTransitionScope.SlideDirection.End)
        }) {
            val annotationViewModel = hiltViewModel<AnnotationViewModel>()
            AnnotationScreen(viewModel = annotationViewModel)
        }

        composable(route = Body.route, enterTransition = {
            slideIntoTransition(direction = AnimatedContentTransitionScope.SlideDirection.Start)
        }, exitTransition = {
            slideOutTransition(direction = AnimatedContentTransitionScope.SlideDirection.End)
        }) {
            val bodyMassIndexViewModel = hiltViewModel<BodyMassIndexViewModel>()
            BodyMassIndexScreen(viewModel = bodyMassIndexViewModel)
        }

        composable(
            route = Exercises.routeWithArgs,
            arguments = Exercises.arguments
        ) { navBackStack ->
            val viewModel = hiltViewModel<ExercisesViewModel>()

            navBackStack.arguments?.getString("bodyPart")?.let {
                ExercisesScreen(bodyPartSelected = it, viewModel = viewModel, navHostController = navController)
            }
        }
    }
}