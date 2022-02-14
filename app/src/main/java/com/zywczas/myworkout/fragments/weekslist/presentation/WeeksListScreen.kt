package com.zywczas.myworkout.fragments.weekslist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zywczas.myworkout.theme.AppTheme

@Composable
fun WeeksListScreen(navController: NavController,) {
    Column {
        Text(
            text = "week list fragment",
            color = MaterialTheme.colors.secondary
        )
        Button(onClick = { navController.navigate("welcome") }) {
            Text(text = "idz do pierwszego fragmentu")
        }
    }
}