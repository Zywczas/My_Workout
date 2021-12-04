package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

import androidx.annotation.StringRes
import org.joda.time.DateTime

@Suppress("CanSealedSubClassBeObject")
sealed class WeeksElements {

    data class Title(@StringRes val title: Int) : WeeksElements()

    data class Week(val id: Long,
                    val name: String,
                    val sequence: Int,
                    val dateStarted: DateTime?,
                    val dateFinished: DateTime?,
                    val isFinished: Boolean,
                    var displayedDates: String = "") : WeeksElements()

    class Settings : WeeksElements()

}