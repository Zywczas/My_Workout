package com.zywczas.myworkout.watch.activities.settings.weeks.domain

import androidx.annotation.StringRes
import com.zywczas.myworkout.watch.R

sealed class SettingsWeeksElements {

    data class Title(@StringRes val title: Int = R.string.training_weeks) : SettingsWeeksElements()

    data class Week(
        val id: Long = 0,
        val name: String,
        val sequence: Int
    ) : SettingsWeeksElements()

    data class AddNewWeek(@StringRes val title: Int = R.string.add_new_week) : SettingsWeeksElements()

}