package com.zywczas.myworkout.watch.activities.settings.weeks.domain

interface SettingsWeeksRepository {

    suspend fun getWeeks(): List<SettingsWeeksElements.Week>

    suspend fun saveNewWeek(week: SettingsWeeksElements.Week)

}