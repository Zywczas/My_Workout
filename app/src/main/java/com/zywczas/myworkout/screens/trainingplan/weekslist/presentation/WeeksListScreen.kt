package com.zywczas.myworkout.screens.trainingplan.weekslist.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zywczas.myworkout.R
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.Week
import com.zywczas.myworkout.theme.AppTheme
import com.zywczas.myworkout.uicomponents.TopAppBar
import com.zywczas.myworkout.theme.largePadding
import com.zywczas.myworkout.theme.mediumPadding
import com.zywczas.myworkout.uicomponents.WeekItem

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

        if (isEmptyPlanMessageVisible.value) {
            Text(
                text = stringResource(R.string.empty_training_plan_weeks),
                modifier = Modifier.padding(start = largePadding, end = largePadding, top = mediumPadding)
            )
        }

        Button(
            onClick = actionAddNextItem,
        ) { //todo poprawic
            Text(text = "dodaj tydzien")
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
        WeeksListScreen(
            weeks = mutableStateListOf(Week(name = "week 1"), Week(name = "week 2"), Week(name = "week 3")),
            isEmptyPlanMessageVisible = mutableStateOf(true),
            actionGetListItems = {},
            actionAddNextItem = {}
        )
    }
}