package com.zywczas.myworkout.watch.activities.settings.weeks.domain

import androidx.annotation.StringRes
import com.zywczas.myworkout.watch.R

sealed class SettingsWeeksElements(@StringRes val resTitle: Int? = null, val stringTitle: String? = null) {

    class Title : SettingsWeeksElements(resTitle = R.string.training_weeks)

    data class Week(private val title: String) : SettingsWeeksElements(stringTitle = title)

    class AddNewWeek : SettingsWeeksElements(resTitle = R.string.add_new_week)

}