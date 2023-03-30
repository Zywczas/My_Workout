package com.zywczas.databasestore.timer

import com.zywczas.databasestore.synchronisation.SynchronisationBusinessCase
import com.zywczas.databasestore.timer.dao.TimerDao
import com.zywczas.databasestore.timer.entities.TimerLocal
import javax.inject.Inject

internal class TimerBusinessCaseImpl @Inject constructor(
    private val timerDao: TimerDao,
    private val synchronisation: SynchronisationBusinessCase
) : TimerBusinessCase {

    override suspend fun getTimer(): TimerLocal = timerDao.getTimer() ?: TimerLocal()

    override suspend fun saveBreakPeriod(seconds: Int) {
        timerDao.insert(TimerLocal(seconds = seconds))
        synchronisation.updateDatabaseTimeStamp()
    }
}
