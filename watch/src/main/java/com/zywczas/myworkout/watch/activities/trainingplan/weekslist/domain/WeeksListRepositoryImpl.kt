package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.WeekEntity
import javax.inject.Inject

class WeeksListRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase,
    private val dateTime: DateTimeProvider
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
//todo poprawic i wyrzicuc ten caly element a podawac funkcji tylko nazwe i sequence
    override suspend fun saveNewWeek(week: WeeksListElements.Week) = trainings.saveNewWeek(
        WeekEntity(
            name = week.name,
            sequence = week.sequence,
            timeStamp = dateTime.now()
        )
    )

}