package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

import com.zywczas.databasestore.trainings.PlannedTrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.WeekEntity
import javax.inject.Inject

class WeeksListRepositoryImpl @Inject constructor(
    private val plannedTrainings: PlannedTrainingsBusinessCase
) : WeeksListRepository {

    override suspend fun getWeeks(): List<WeeksElements.Week> = plannedTrainings.getWeeks().map { it.toDomain() }

    private fun WeekEntity.toDomain() = WeeksElements.Week(
        id = id,
        name = name,
        sequence = sequence,
        dateStarted = dateStarted,
        dateFinished = dateFinished,
        isFinished = isFinished
    )

}