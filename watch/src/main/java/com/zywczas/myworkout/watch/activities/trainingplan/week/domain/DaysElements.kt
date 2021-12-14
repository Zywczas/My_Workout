package com.zywczas.myworkout.watch.activities.trainingplan.week.domain

import androidx.annotation.StringRes
import com.zywczas.myworkout.watch.R
import org.joda.time.DateTime

sealed class DaysElements {

    data class WeekHeader(
        val weekName: String = "",
        val dateStarted: DateTime? = null,
        val dateFinished: DateTime? = null,
        var displayedDate: String = ""
    ) : DaysElements()

    data class Day(
        val id: Long = 0L,
        val name: String = "",
        val sequence: Int = 0,
        val isFinished: Boolean = false,
        val dateFinished: DateTime? = null,
        var displayedDate: String = ""
    ) : DaysElements()

    data class AddNewDay(@StringRes val title: Int = R.string.add_new_day) : DaysElements()

    data class CopyWeek(@StringRes val title: Int = R.string.copy_week) : DaysElements()

}