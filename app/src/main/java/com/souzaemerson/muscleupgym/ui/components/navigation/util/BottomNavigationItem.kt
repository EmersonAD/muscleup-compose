package com.souzaemerson.muscleupgym.ui.components.navigation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem.Annotation
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem.Body
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem.Home

val items = listOf(Home, Annotation, Body)

sealed class BottomNavigationItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    data object Annotation : BottomNavigationItem(
        label = "Annotations",
        selectedIcon = Icons.Filled.Create,
        unselectedIcon = Icons.Outlined.Create,
        route = "annotation"
    )

    data object Home : BottomNavigationItem(
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = "home"
    )

    data object Body :
        BottomNavigationItem(
            label = "IMC",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face,
            route = "imc"
        )
}