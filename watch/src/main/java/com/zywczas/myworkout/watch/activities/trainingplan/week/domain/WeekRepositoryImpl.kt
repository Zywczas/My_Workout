package com.zywczas.myworkout.watch.activities.trainingplan.week.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.DayEntity
import javax.inject.Inject

class WeekRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : WeekRepository {

    override suspend fun getDays(weekId: Long): List<DaysElements.Day> = trainings.getDays(weekId).map { it.toDomain() }

    private fun DayEntity.toDomain() = DaysElements.Day(
        id = id,
        name = name,
        isFinished = isFinished
    )

}