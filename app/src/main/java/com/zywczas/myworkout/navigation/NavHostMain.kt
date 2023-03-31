package com.zywczas.myworkout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zywczas.myworkout.screens.trainingplan.weekslist.presentation.WeeksListScreen

@Composable
fun NavHostMain() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.WEEKS_LIST.name) {
        composable(Destinations.WEEKS_LIST.name) { WeeksListScreen() }
    }
}
