package com.souzaemerson.muscleupgym.ui.extensions

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigateTo(route: String) {
    this.navigate(route) {
        popUpTo(this@navigateTo.graph.findStartDestination().id){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}