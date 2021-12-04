package com.zywczas.myworkout.watch.activities.settings.main.domain

import androidx.annotation.StringRes
import com.zywczas.myworkout.watch.R

sealed class SettingsMainElements(@StringRes val title: Int) {

    class Title : SettingsMainElements(R.string.settings)

    class Trainings : SettingsMainElements(R.string.trainings)

    class BreakInterval : SettingsMainElements(R.string.break_interval)

}