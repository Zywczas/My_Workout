package com.zywczas.myworkout.screens.trainingplan.weekslist.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zywczas.common.extetions.logD
import com.zywczas.myworkout.R
import com.zywczas.myworkout.composeitems.AppTheme
import com.zywczas.myworkout.composeitems.TopAppBar
import com.zywczas.myworkout.composeitems.listitems.WeekItem
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.Week

@Composable
fun WeeksListScreen(
    navController: NavController,
    viewModel: WeeksListViewModel = hiltViewModel()
) {
    WeeksListScreen(
        weeks = viewModel.weeks,
        isEmptyPlanMessageVisible = viewModel.isEmptyPlanMessageVisible,
        actionGetListItems = { viewModel.getWeeksList() },
        actionAddNextItem = { viewModel.addNewWeek() }
    )
}

@Composable
private fun WeeksListScreen(
    weeks: SnapshotStateList<Week>,
    isEmptyPlanMessageVisible: State<Boolean>,
    actionGetListItems: ()->Unit,
    actionAddNextItem: ()->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(R.string.title_training_weeks)

        Button(onClick = actionAddNextItem) {
            Text(text = "dodaj")
        }

        if (isEmptyPlanMessageVisible.value) {
            Text(text = "pusta lista") //todo dokonczyc
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(weeks) {
                WeekItem(it)
            }
        }

        LaunchedEffect(Unit) {
            actionGetListItems.invoke()
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