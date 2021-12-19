package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

import androidx.annotation.StringRes
import com.zywczas.myworkout.watch.R
import org.joda.time.DateTime

@Suppress("CanSealedSubClassBeObject")
sealed class WeeksListElements {

    data class Title(@StringRes val title: Int = R.string.planned_trainings) : WeeksListElements()

    data class Week(
        val id: Long = 0L,
        val name: String = "",
        val sequence: Int = 0,
        val dateStarted: DateTime? = null,
        val dateFinished: DateTime? = null,
        val isFinished: Boolean = false,
        var displayedDates: String = ""
    ) : WeeksListElements()

    data class AddNewWeek(@StringRes val title: Int = R.string.add_new_week) : WeeksListElements()

}