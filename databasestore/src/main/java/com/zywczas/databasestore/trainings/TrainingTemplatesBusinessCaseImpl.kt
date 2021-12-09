package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.di.modules.DatabaseModule.TrainingTemplates
import com.zywczas.databasestore.trainings.dao.CardioDao
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.timer.dao.TimerDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import com.zywczas.databasestore.trainings.relations.WeekRelations
import javax.inject.Inject

internal class TrainingTemplatesBusinessCaseImpl
@Inject constructor(@TrainingTemplates private val weekDao: WeekDao,
                    @TrainingTemplates private val dayDao: DayDao,
                    @TrainingTemplates private val cardioDao: CardioDao,
                    @TrainingTemplates private val exerciseDao: ExerciseDao
) : TrainingTemplatesBusinessCase {
//todo chyba nie potrzebne
//    override suspend fun getWeekRelationsList(): List<WeekRelations> = weekDao.getWeekRelationsList()

}