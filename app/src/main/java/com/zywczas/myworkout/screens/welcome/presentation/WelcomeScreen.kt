package com.zywczas.myworkout.screens.welcome.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zywczas.myworkout.navigation.MainDestinations

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "welcome fragment",
            color = MaterialTheme.colors.primary
        )
        Button(onClick = {
            navController.navigate(MainDestinations.WeeksList.route) {
                popUpTo(MainDestinations.Welcome.route) { inclusive = true }
            }
        }) {
            Text(text = "idz do WeeksList fragmentu")
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "WelcomeFragmentScreen",
)
@Composable
fun PreviewWelcomeFragmentScreen() {

}

