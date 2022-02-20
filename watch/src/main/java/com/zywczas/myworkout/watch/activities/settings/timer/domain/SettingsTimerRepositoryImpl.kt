package com.zywczas.myworkout.watch.activities.settings.timer.domain

import com.zywczas.databasestore.timer.TimerBusinessCase
import javax.inject.Inject

class SettingsTimerRepositoryImpl @Inject constructor(
    private val timerBusinessCase: TimerBusinessCase
) : SettingsTimerRepository {

    override suspend fun getBreakPeriodInSeconds(): Int = timerBusinessCase.getTimer().seconds

    override suspend fun saveBreakPeriod(seconds: Int) {
        timerBusinessCase.saveBreakPeriod(seconds)
    }

}