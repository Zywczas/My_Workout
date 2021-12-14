package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.dao.CardioDao
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity
import javax.inject.Inject

internal class TrainingsBusinessCaseImpl
@Inject constructor(private val weekDao: WeekDao,
                    private val dayDao: DayDao,
                    private val cardioDao: CardioDao,
                    private val exerciseDao: ExerciseDao
) : TrainingsBusinessCase {

    override suspend fun getWeeks(): List<WeekEntity> = weekDao.getWeeks()

    override suspend fun saveNewWeek(week: WeekEntity) {
        weekDao.insert(week)
    }

    override suspend fun getWeek(id: Long): WeekEntity = weekDao.getWeek(id)

    override suspend fun getDays(weekId: Long): List<DayEntity> = dayDao.getDays(weekId)

}