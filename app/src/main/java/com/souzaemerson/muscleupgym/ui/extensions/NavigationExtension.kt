package com.souzaemerson.muscleupgym.ui.extensions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigateTo(route: String) {
    if (currentDestination?.route != route) {
        navigate(route) {
            popUpTo(this@navigateTo.graph.findStartDestination().id) {
                saveState = true

            }
            launchSingleTop = true
        }
    }
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIntoTransition(
    direction: AnimatedContentTransitionScope.SlideDirection,
    durationInMillis: Int = 700
) = slideIntoContainer(
    direction,
    tween(durationInMillis)
)

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutTransition(
    direction: AnimatedContentTransitionScope.SlideDirection,
    durationInMillis: Int = 700
) = slideOutOfContainer(
    direction,
    tween(durationInMillis)
)