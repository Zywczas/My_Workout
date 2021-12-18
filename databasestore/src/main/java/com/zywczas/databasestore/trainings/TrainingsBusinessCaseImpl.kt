package com.zywczas.databasestore.trainings

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.databasestore.trainings.dao.CardioDao
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import com.zywczas.databasestore.trainings.entities.CardioEntity
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.ExerciseEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity
import com.zywczas.databasestore.trainings.relations.DayRelations
import com.zywczas.databasestore.trainings.relations.WeekRelations
import javax.inject.Inject

internal class TrainingsBusinessCaseImpl
@Inject constructor(
    private val weekDao: WeekDao,
    private val dayDao: DayDao,
    private val cardioDao: CardioDao,
    private val exerciseDao: ExerciseDao,
    private val dateTime: DateTimeProvider
) : TrainingsBusinessCase {

    override suspend fun getWeeks(): List<WeekEntity> = weekDao.getWeeks()

    override suspend fun findNextWeekPosition(): Int = weekDao.getWeeks().maxByOrNull { it.sequence }?.let { it.sequence + 1 } ?: 1

    override suspend fun saveNewWeek(week: WeekEntity) {
        weekDao.insert(week)
    }

    override suspend fun getWeek(id: Long): WeekEntity = weekDao.getWeek(id)

    override suspend fun getDays(weekId: Long): List<DayEntity> = dayDao.getDays(weekId)

    override suspend fun saveNewDay(day: DayEntity) {
        dayDao.insert(day)
    }

    override suspend fun copyWeekAndTrainings(weekId: Long) {
        val weekRelationsToCopy = weekDao.getWeekRelations(weekId)
        val newWeekRelations = copyWeekRelations(weekRelationsToCopy)
    }

    private suspend fun copyWeekRelations(oldWeekRelations: WeekRelations): WeekRelations {
        val newWeek = WeekEntity(
            name = oldWeekRelations.week.name,
            sequence = findNextWeekPosition(),
            timeStamp = dateTime.now()
        )
        return WeekRelations(
            week = newWeek,
            days = copyDaysRelations(oldWeekRelations.days)
        )
    }

    private suspend fun copyDaysRelations(oldDaysRelations: List<DayRelations>): List<DayRelations> {
        val copiedDaysRelations = mutableListOf<DayRelations>()
        oldDaysRelations.forEach {
            val newDay = DayEntity(
                name = it.day.name,
                sequence = it.day.sequence,
                timeStamp = dateTime.now()
            )
            val newDayRelation = DayRelations(
                day = newDay,
                exercises = copyExercices(it.exercises),
                cardio = copyCardio(it.cardio)
            )
            copiedDaysRelations.add(newDayRelation)
        }
        return copiedDaysRelations
    }

    private suspend fun copyExercices(oldExercises: List<ExerciseEntity>): List<ExerciseEntity> {

    }


    private suspend fun copyCardio(oldCardio: CardioEntity?): CardioEntity? {

    }

}