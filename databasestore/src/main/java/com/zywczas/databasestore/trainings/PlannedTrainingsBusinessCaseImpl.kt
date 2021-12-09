package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.di.modules.DatabaseModule.PlannedTrainings
import com.zywczas.databasestore.trainings.dao.CardioDao
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.timer.dao.TimerDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import com.zywczas.databasestore.trainings.entities.WeekEntity
import javax.inject.Inject

internal class PlannedTrainingsBusinessCaseImpl
@Inject constructor(@PlannedTrainings private val weekDao: WeekDao,
                    @PlannedTrainings private val dayDao: DayDao,
                    @PlannedTrainings private val cardioDao: CardioDao,
                    @PlannedTrainings private val exerciseDao: ExerciseDao
) : PlannedTrainingsBusinessCase {
//todo ta funkcja moze wogole nie potrzebnna
//    override suspend fun getWeekRelationsList(): List<WeekRelations> = weekDao.getWeekRelationsList()

    override suspend fun getWeeks(): List<WeekEntity> = weekDao.getWeeks()

}