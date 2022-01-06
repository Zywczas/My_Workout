package com.zywczas.myworkout.watch.activities.trainingplan.week.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.DayLocal
import com.zywczas.databasestore.trainings.entities.WeekLocal
import javax.inject.Inject

class WeekRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : WeekRepository {

    override suspend fun getWeekHeader(id: Long): WeekElements.WeekHeader = trainings.getWeek(id).toDomain()

    private fun WeekLocal.toDomain() = WeekElements.WeekHeader(
        weekName = name,
        dateStarted = dateStarted,
        dateFinished = dateFinished
    )

    override suspend fun getDays(weekId: Long): List<WeekElements.Day> = trainings.getDays(weekId).map { it.toDomain() }

    private fun DayLocal.toDomain() = WeekElements.Day(
        id = id,
        name = name,
        sequence = sequence,
        isFinished = dateFinished != null,
        dateStarted = dateStarted,
        dateFinished = dateFinished,
        isCardioDone = isCardioDone
    )

    override suspend fun saveNewDay(name: String, weekId: Long, sequence: Int) = trainings.saveDay(
        DayLocal(
        foreignWeekId = weekId,
        name = name,
        sequence = sequence
    ))

    override suspend fun copyWeekAndTrainings(weekId: Long) = trainings.copyWeekAndTrainings(weekId)

    override suspend fun deleteWeek(id: Long) = trainings.deleteWeek(id)

}