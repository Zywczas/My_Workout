package com.zywczas.myworkout.watch.activities.settings.weeks.domain

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.databasestore.trainings.TrainingTemplatesBusinessCase
import com.zywczas.databasestore.trainings.entities.WeekEntity
import javax.inject.Inject

class SettingsWeeksRepositoryImpl @Inject constructor(
    private val trainingTemplates: TrainingTemplatesBusinessCase,
    private val dateTime: DateTimeProvider
) : SettingsWeeksRepository {

    override suspend fun getWeeks(): List<SettingsWeeksElements.Week> = trainingTemplates.getWeeks().map { it.toDomain() }

    private fun WeekEntity.toDomain() = SettingsWeeksElements.Week(
        id = id,
        name = name,
        sequence = sequence
    )

    override suspend fun saveNewWeek(week: SettingsWeeksElements.Week) = trainingTemplates.saveNewWeek(
        WeekEntity(
            name = week.name,
            sequence = week.sequence,
            timeStamp = dateTime.now()
        )
    )

}