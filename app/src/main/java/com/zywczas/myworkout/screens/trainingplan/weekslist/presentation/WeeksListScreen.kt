package com.zywczas.myworkout.screens.trainingplan.weekslist.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.zywczas.common.utils.Action
import com.zywczas.myworkout.R
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.Week
import com.zywczas.myworkout.theme.AppTheme
import com.zywczas.myworkout.theme.largePadding
import com.zywczas.myworkout.theme.mediumPadding
import com.zywczas.myworkout.uicomponents.FloatingPlusButton
import com.zywczas.myworkout.uicomponents.Toolbar
import com.zywczas.myworkout.uicomponents.WeekListItem
import kotlinx.coroutines.launch

@Composable
fun WeeksListScreen(
    viewModel: WeeksListViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getWeeksList()
    }
    WeeksListScreen(
        weeks = viewModel.weeksList,
        isEmptyPlanMessageVisible = viewModel.isEmptyPlanMessageVisible,
        onPlusButtonCLick = viewModel::addNewWeek
    )
}

@Composable
private fun WeeksListScreen(
    weeks: List<Week>,
    isEmptyPlanMessageVisible: Boolean,
    onPlusButtonCLick: Action,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { Toolbar(R.string.title_training_weeks) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingPlusButton {
                onPlusButtonCLick()
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            if (isEmptyPlanMessageVisible) {
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
                    WeekListItem(it)
                }
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
private fun Preview() {
    AppTheme {
        WeeksListScreen(
            weeks = listOf(Week(name = "week 1"), Week(name = "week 2"), Week(name = "week 3")),
            isEmptyPlanMessageVisible = true,
            onPlusButtonCLick = {}
        )
    }
}
