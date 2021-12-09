package com.zywczas.myworkout.watch.services.timer

import com.zywczas.databasestore.timer.TimerBusinessCase
import javax.inject.Inject

class TimerServiceRepositoryImpl @Inject constructor(
    private val timerBusinessCase: TimerBusinessCase
) : TimerServiceRepository {

//    override suspend fun getBreakPeriodInSeconds(): Int = timerBusinessCase.getTimer().seconds //todo tak ma byc ale na razie dla testow dam tak:
    override suspend fun getBreakPeriodInSeconds(): Int = 10

}