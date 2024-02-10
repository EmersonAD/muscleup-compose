package com.souzaemerson.muscleupgym.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.souzaemerson.muscleupgym.ui.components.TopBar
import com.souzaemerson.muscleupgym.ui.components.navigation.BottomNavigation
import com.souzaemerson.muscleupgym.ui.extensions.navigateTo
import com.souzaemerson.muscleupgym.ui.navigation.MuscleUpNavHost
import com.souzaemerson.muscleupgym.ui.theme.MuscleupgymTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuscleUpApp()
        }
    }
}

@Composable
fun MuscleUpApp() {
    MuscleupgymTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    navigateTo = { route ->
                        navController.navigateTo(route)
                    }
                )
            },
            topBar = { TopBar() },
            containerColor = Color.DarkGray
        ) {
            MuscleUpNavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MuscleupgymTheme {

    }
}