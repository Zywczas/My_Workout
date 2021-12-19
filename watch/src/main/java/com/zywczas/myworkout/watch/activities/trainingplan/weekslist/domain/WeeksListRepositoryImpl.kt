package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.WeekEntity
import javax.inject.Inject

class WeeksListRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : WeeksListRepository {

    override suspend fun getWeeks(): List<WeeksListElements.Week> = trainings.getWeeks().map { it.toDomain() }

    private fun WeekEntity.toDomain() = WeeksListElements.Week(
        id = id,
        name = name,
        sequence = sequence,
        dateStarted = dateStarted,
        dateFinished = dateFinished,
        isFinished = isFinished
    )

    override suspend fun saveNewWeek(name: String) = trainings.saveNewWeek(name)

}