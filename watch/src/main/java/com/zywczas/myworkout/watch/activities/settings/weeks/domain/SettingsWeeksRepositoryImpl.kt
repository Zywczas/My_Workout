package com.zywczas.myworkout.watch.activities.settings.weeks.domain

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.WeekEntity
import javax.inject.Inject

class SettingsWeeksRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase,
    private val dateTime: DateTimeProvider
) : SettingsWeeksRepository {

    override suspend fun getWeeks(): List<SettingsWeeksElements.Week> = trainings.getWeeks().map { it.toDomain() }

    private fun WeekEntity.toDomain() = SettingsWeeksElements.Week(
        id = id,
        name = name,
        sequence = sequence
    )

    override suspend fun saveNewWeek(week: SettingsWeeksElements.Week) = trainings.saveNewWeek(
        WeekEntity(
            name = week.name,
            sequence = week.sequence,
            timeStamp = dateTime.now()
        )
    )

}