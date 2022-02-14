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
    WelcomeScreen(
        navigateToWeekListAction = {
            navController.navigate(MainDestinations.WeeksList.route) {
                popUpTo(MainDestinations.Welcome.route) { inclusive = true }
            }
        }
    )
}

@Composable
private fun WelcomeScreen(
    navigateToWeekListAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "welcome fragment",
            color = MaterialTheme.colors.primary
        )
        Button(onClick = navigateToWeekListAction) {
            Text(text = "idz do WeeksList fragmentu")
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "WelcomeScreen UI_MODE_NIGHT_NO",
)
@Composable
private fun PreviewWelcomeScreenDayMode() {
    WelcomeScreen(
        navigateToWeekListAction = {}
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "WelcomeScreen UI_MODE_NIGHT_YES",
)
@Composable
private fun PreviewWelcomeScreenNighMode() {
    WelcomeScreen(
        navigateToWeekListAction = {}
    )
}

