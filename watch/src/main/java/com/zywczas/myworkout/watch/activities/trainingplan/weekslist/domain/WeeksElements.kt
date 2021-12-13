package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

import androidx.annotation.StringRes
import com.zywczas.myworkout.watch.R
import org.joda.time.DateTime

@Suppress("CanSealedSubClassBeObject")
sealed class WeeksElements {

    data class Title(@StringRes val title: Int = R.string.planned_trainings) : WeeksElements()

    data class Week(
        val id: Long = 0L,
        val name: String = "",
        val sequence: Int = 0,
        val dateStarted: DateTime? = null,
        val dateFinished: DateTime? = null,
        val isFinished: Boolean = false,
        var displayedDates: String = ""
    ) : WeeksElements()

    data class AddNewWeek(@StringRes val title: Int = R.string.add_new_week) : WeeksElements()

//    class Settings : WeeksElements() //todo chyba nie potrzebne

}