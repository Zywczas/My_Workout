package com.zywczas.myworkout.fragments.welcome.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zywczas.myworkout.theme.AppTheme

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    Column {
        Text(
            text = "welcome fragment",
            color = MaterialTheme.colors.primary
        )
        Button(onClick = { navController.navigate("weekList") }) {
            Text(text = "idz do drugiego fragmentu")
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
//    WelcomeFragmentScreen(WelcomeViewModel())
}

