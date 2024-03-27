package com.souzaemerson.muscleupgym.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.souzaemerson.muscleupgym.ui.components.TopBar
import com.souzaemerson.muscleupgym.ui.components.navigation.BottomNavigation
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem.Home
import com.souzaemerson.muscleupgym.ui.extensions.navigateTo
import com.souzaemerson.muscleupgym.ui.navigation.MuscleUpNavHost
import com.souzaemerson.muscleupgym.ui.screens.splash.SplashScreen
import com.souzaemerson.muscleupgym.ui.theme.MuscleupgymTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MuscleUpApp(navController = navController)
        }
    }
}

@Composable
fun MuscleUpApp(navController: NavHostController) {
    var isReadyToGo by rememberSaveable { mutableStateOf(false) }

    MuscleupgymTheme {
        if (isReadyToGo) {
            MuscleContent(navController = navController)
        } else {
            SplashScreen { splashState ->
                isReadyToGo = splashState
            }
        }
    }
}

@Composable
fun MuscleContent(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navigateTo = { route ->
                    navController.navigateTo(route)
                }
            )
        },
        topBar = {
            TopBar()
        },
        containerColor = Color.DarkGray
    ) {
        MuscleUpNavHost(
            navController = navController,
            startDestination = Home.route,
            modifier = Modifier.padding(it)
        )
    }
}