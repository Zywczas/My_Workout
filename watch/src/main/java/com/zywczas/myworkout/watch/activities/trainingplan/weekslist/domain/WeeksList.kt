package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

import androidx.annotation.StringRes
import org.joda.time.DateTime

sealed class WeeksList {

    data class Title(@StringRes val title: Int) : WeeksList()

    data class Week(val id: Long,
                    val name: String,
                    val sequence: Int,
                    val dateStarted: DateTime?,
                    val dateFinished: DateTime?,
                    val isFinished: Boolean,
                    var displayedDates: String = "") : WeeksList()

    object Settings : WeeksList()

}