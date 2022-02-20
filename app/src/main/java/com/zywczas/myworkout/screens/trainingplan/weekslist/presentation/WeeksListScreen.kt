package com.zywczas.myworkout.screens.trainingplan.weekslist.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zywczas.common.extetions.logD
import com.zywczas.myworkout.R
import com.zywczas.myworkout.composeitems.AppTheme
import com.zywczas.myworkout.composeitems.TopAppBar
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.Week

@Composable
fun WeeksListScreen(
    navController: NavController,
    viewModel: WeeksListViewModel = hiltViewModel()
) {
    WeeksListScreen(
        weeks = viewModel.weeks,
        actionAddNextItem = { viewModel.getWeeksList() }
    )

}

@Composable
private fun WeeksListScreen(
    weeks: List<Week>,
    actionAddNextItem: ()->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(R.string.title_training_weeks)

        LazyColumn {
            items(weeks) { week -> //todo sprawdzic czy jak dodaje kolejne elementy to te poprzednie tez sa odswiezane

            }
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
//        WeeksListScreen(listOf("raz", "dwa", "trzy")){}
    }
}