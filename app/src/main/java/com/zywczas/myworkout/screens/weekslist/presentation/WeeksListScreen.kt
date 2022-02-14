package com.zywczas.myworkout.screens.weekslist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zywczas.myworkout.navigation.MainDestinations

@Composable
fun WeeksListScreen(
    navController: NavController,
    viewModel: WeeksListViewModel = hiltViewModel()
) {
    Column {
        Text(
            text = "week list fragment",
            color = MaterialTheme.colors.primary
        )
        Button(onClick = { navController.navigate(MainDestinations.Welcome.route) }) {
            Text(text = "idz do Welcome fragmentu")
        }
    }
}