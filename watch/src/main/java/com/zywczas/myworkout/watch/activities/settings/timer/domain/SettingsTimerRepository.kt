package com.zywczas.myworkout.watch.activities.settings.timer.domain

interface SettingsTimerRepository {

    suspend fun getBreakPeriodInSeconds(): Int

}