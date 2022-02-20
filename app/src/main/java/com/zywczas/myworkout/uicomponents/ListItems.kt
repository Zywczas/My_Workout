package com.zywczas.myworkout.uicomponents

import android.content.res.Configuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.Week
import com.zywczas.myworkout.theme.AppTheme

@Composable
fun WeekItem(week: Week) {
    Text(text = "${week.name} - ${week.sequence}")
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "WeekItem DayMode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "WeekItem NightMode"
)
@Composable
private fun PreviewWeekItem() {
    AppTheme {
        WeekItem(Week())
    }
}