package com.zywczas.myworkout.screens.trainingplan.weekslist.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.WeekLocal
import javax.inject.Inject

class WeeksListRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : WeeksListRepository {

    override suspend fun getWeeks(): List<Week> = trainings.getWeeks().map { it.toDomain() }

    private fun WeekLocal.toDomain() = Week(
        id = id,
        name = name,
        sequence = sequence,
        copyVersion = copyVersion,
        dateStarted = dateStarted,
        dateFinished = dateFinished,
        isFinished = dateFinished != null
    )

    override suspend fun deleteWeek(id: Long) {
        trainings.deleteWeek(id)
    }

    override suspend fun saveNewWeek(name: String) {
        trainings.saveNewWeek(name)
    }

}