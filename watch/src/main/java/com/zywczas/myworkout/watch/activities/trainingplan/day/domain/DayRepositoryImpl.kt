package com.zywczas.myworkout.watch.activities.trainingplan.day.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.ExerciseEntity
import javax.inject.Inject

class DayRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase,
) : DayRepository {

    override suspend fun getExercises(dayId: Long): List<DayElements.Exercise> = trainings.getExercises(dayId).map { it.toDayHeader() }

    private fun ExerciseEntity.toDayHeader() = DayElements.Exercise(
        id = id,
        dayId = foreignDayId,
        name = name,
        sequence = sequence,
        isFinished = isFinished,
        currentSet = currentSet
    )

    override suspend fun getDayHeader(dayId: Long): DayElements.DayHeader = trainings.getDay(dayId).toDayHeader()

    private fun DayEntity.toDayHeader(): DayElements.DayHeader = DayElements.DayHeader(
        dateStarted = dateStarted,
        dateFinished = dateFinished
    )

    override suspend fun isCardioDone(dayId: Long): Boolean = trainings.isCardioDone(dayId)

    override suspend fun copyDayAndTrainings(dayId: Long) = trainings.copyDayAndTrainingsInTheSameWeek(dayId)

    override suspend fun addCardio(dayId: Long) = trainings.addCardio(dayId)

    override suspend fun getWeekId(dayId: Long): Long = trainings.getWeekId(dayId)

    override suspend fun deleteDay(id: Long) = trainings.deleteDay(id)

    override suspend fun getDays(weekId: Long): List<Day> = trainings.getDays(weekId).map { it.toDomain() }

    private fun DayEntity.toDomain() = Day(isFinished = isFinished)

    override suspend fun markDayAsStarted(id: Long) = trainings.markDayAsStarted(id)

    override suspend fun markWeekAsStartedIfNotStarted(dayId: Long) = trainings.markWeekAsStartedIfNotStarted(dayId)

    override suspend fun markWeekAsFinished(id: Long) = trainings.markWeekAsFinished(id)

}