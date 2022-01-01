package com.zywczas.myworkout.watch.activities.trainingplan.day.domain

import androidx.annotation.StringRes
import com.zywczas.myworkout.watch.R
import org.joda.time.DateTime

@Suppress("CanSealedSubClassBeObject")
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

    class Cardio : DayElements()

    data class AddNewExercise(@StringRes val title: Int = R.string.add_new_exercise) : DayElements()

    data class AddCardio(@StringRes val title: Int = R.string.add_cardio) : DayElements()

    data class CopyDay(@StringRes val title: Int = R.string.copy_day) : DayElements()

    data class DeleteDay(@StringRes val title: Int = R.string.delete_day) : DayElements()

}