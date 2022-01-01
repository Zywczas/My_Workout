package com.zywczas.databasestore.timer

import com.zywczas.databasestore.synchronisation.SynchronisationBusinessCase
import com.zywczas.databasestore.timer.dao.TimerDao
import com.zywczas.databasestore.timer.entities.TimerEntity
import javax.inject.Inject

internal class TimerBusinessCaseImpl @Inject constructor(
    private val timerDao: TimerDao,
    private val synchronisation: SynchronisationBusinessCase
) : TimerBusinessCase {

    override suspend fun getTimer(): TimerEntity = timerDao.getTimer() ?: TimerEntity()

    override suspend fun saveBreakPeriod(seconds: Int) {
        timerDao.insert(TimerEntity(seconds = seconds))
        synchronisation.updateDatabaseTimeStamp()
    }

}