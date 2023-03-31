package com.zywczas.myworkout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zywczas.myworkout.screens.trainingplan.weekslist.presentation.WeeksListScreen
import com.zywczas.myworkout.screens.welcome.presentation.WelcomeScreen

@Composable
fun NavHostMain() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.WELCOME.name) {
        composable(Destinations.WELCOME.name) {
            WelcomeScreen(
                navigateToNextScreen = {
                    navController.navigate(Destinations.WEEKS_LIST.name) {
                        popUpTo(Destinations.WELCOME.name) { inclusive = true }
                    }
                }
            )
        }
        composable(Destinations.WEEKS_LIST.name) { WeeksListScreen() }
    }
}
