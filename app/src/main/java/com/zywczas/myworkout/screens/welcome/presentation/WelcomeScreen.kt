package com.zywczas.myworkout.screens.welcome.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zywczas.myworkout.R
import com.zywczas.myworkout.navigation.MainDestinations
import com.zywczas.myworkout.theme.AppTheme

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {

    WelcomeScreen()

    var hasHandledNavigation by remember { mutableStateOf(false) }
    if (viewModel.shouldGoToNextScreen && hasHandledNavigation.not()) {
        hasHandledNavigation = true
        navigateToWeekList(navController)
    }

}

private fun navigateToWeekList(navController: NavController){
    navController.navigate(MainDestinations.WeeksList.route) {
        popUpTo(MainDestinations.Welcome.route) { inclusive = true }
    }
}

@Composable
private fun WelcomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.train_hard_win_easily),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h3
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "WelcomeScreen UI_MODE_NIGHT_NO",

)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "WelcomeScreen UI_MODE_NIGHT_YES",
)
@Composable
private fun PreviewWelcomeScreen() {
    AppTheme {
        WelcomeScreen()
    }
}


