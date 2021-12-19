package com.zywczas.myworkout.watch.activities.trainingplan.week.domain

import androidx.annotation.StringRes
import com.zywczas.myworkout.watch.R
import org.joda.time.DateTime

sealed class WeekElements {

    data class WeekHeader(
        val weekName: String = "",
        val dateStarted: DateTime? = null,
        val dateFinished: DateTime? = null,
        var displayedDate: String = ""
    ) : WeekElements()

    data class Day(
        val id: Long = 0L,
        val name: String = "",
        val sequence: Int = 0,
        val isFinished: Boolean = false,
        val dateStarted: DateTime? = null,
        val dateFinished: DateTime? = null,
        var displayedDate: String = ""
    ) : WeekElements()

    data class AddNewDay(@StringRes val title: Int = R.string.add_new_day) : WeekElements()

    data class CopyWeek(@StringRes val title: Int = R.string.copy_week) : WeekElements()

}