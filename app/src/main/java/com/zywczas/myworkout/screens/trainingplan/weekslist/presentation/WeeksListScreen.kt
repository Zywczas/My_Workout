package com.zywczas.myworkout.screens.trainingplan.weekslist.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import kotlinx.coroutines.launch

@Composable
fun WeeksListScreen(
    navController: NavController,
    viewModel: WeeksListViewModel = hiltViewModel()
) {
    WeeksListScreen(
        weeks = viewModel.weeksList,
        isEmptyPlanMessageVisible = viewModel.isEmptyPlanMessageVisible,
        actionGetWeeks = { viewModel.getWeeksList() },
        actionAddNewWeek = { viewModel.addNewWeek() }
    )
}

@Composable
private fun WeeksListScreen(
    weeks: SnapshotStateList<Week>,
    isEmptyPlanMessageVisible: State<Boolean>,
    actionGetWeeks: ()->Unit,
    actionAddNewWeek: ()->Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopAppBar(R.string.title_training_weeks) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    actionAddNewWeek.invoke()
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }) {
                Icon(imageVector = Icons.Default.Add, null)
            }}
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            if (isEmptyPlanMessageVisible.value) {
                Text(
                    text = stringResource(R.string.empty_training_plan_weeks),
                    modifier = Modifier.padding(start = largePadding, end = largePadding, top = mediumPadding)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                items(weeks) {
                    WeekItem(it)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        actionGetWeeks.invoke()
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
            actionGetWeeks = {},
            actionAddNewWeek = {}
        )
    }
}