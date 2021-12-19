package com.zywczas.myworkout.watch.activities.trainingplan.day.domain

import androidx.annotation.StringRes
import com.zywczas.myworkout.watch.R
import org.joda.time.DateTime

sealed class DayElements {

    data class DayHeader(
        val dateStarted: DateTime? = null,
        val dateFinished: DateTime? = null,
        var displayedDate: String = ""
        ) : DayElements()

    data class GoToExercise(@StringRes val title: Int) : DayElements()

    data class Exercise(
        val id: Long,
        val name: String,
        val sequence: Int,
        val isFinished: Boolean
    ) : DayElements()

    data class AddNewExercise(@StringRes val title: Int = R.string.add_new_exercise) : DayElements()

    data class CopyDay(@StringRes val title: Int = R.string.copy_day) : DayElements()

}