package com.zywczas.myworkout.screens.weekslist.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zywczas.myworkout.R
import com.zywczas.myworkout.composeitems.AppTheme
import com.zywczas.myworkout.composeitems.TopAppBar

@Composable
fun WeeksListScreen(
    navController: NavController,
    viewModel: WeeksListViewModel = hiltViewModel()
) {
    WeeksListScreen()

}

@Composable
private fun WeeksListScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(R.string.title_training_weeks)

        Button(onClick = {

        }) {

        }

    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Screen DayMode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Screen NightMode"
)
@Composable
private fun PreviewScreen() {
    AppTheme {
        WeeksListScreen()
    }
}