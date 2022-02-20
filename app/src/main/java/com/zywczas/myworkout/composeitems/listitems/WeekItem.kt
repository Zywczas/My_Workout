package com.zywczas.myworkout.composeitems.listitems

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.Week

@Composable
fun WeekItem(week: Week) {
    Text(text = "${week.name} - ${week.sequence}")
}