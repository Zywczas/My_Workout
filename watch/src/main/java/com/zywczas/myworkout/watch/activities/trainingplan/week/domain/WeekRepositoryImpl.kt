package com.zywczas.myworkout.watch.activities.trainingplan.week.domain

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity
import javax.inject.Inject

class WeekRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase,
    private val dateTime: DateTimeProvider
) : WeekRepository {

    override suspend fun getWeek(id: Long): DaysElements.WeekHeader = trainings.getWeek(id).toDomain()

    private fun WeekEntity.toDomain() = DaysElements.WeekHeader(
        weekName = name,
        dateStarted = dateStarted,
        dateFinished = dateFinished
    )

    override suspend fun getDays(weekId: Long): List<DaysElements.Day> = trainings.getDays(weekId).map { it.toDomain() }

    private fun DayEntity.toDomain() = DaysElements.Day(
        id = id,
        name = name,
        isFinished = isFinished
    )

    override suspend fun saveNewDay(name: String, weekId: Long, sequence: Int) = trainings.saveNewDay(
        DayEntity(
        foreignWeekId = weekId,
        name = name,
        sequence = sequence,
        timeStamp = dateTime.now()
    ))

}