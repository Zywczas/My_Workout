package com.zywczas.myworkout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zywczas.myworkout.screens.weekslist.presentation.WeeksListScreen
import com.zywczas.myworkout.screens.welcome.presentation.WelcomeScreen

@Composable
fun NavHostMain() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainDestinations.Welcome.route) {
        composable(MainDestinations.Welcome.route) { WelcomeScreen(navController = navController) }
        composable(MainDestinations.WeeksList.route) { WeeksListScreen(navController = navController) }
    }
}