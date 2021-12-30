package com.zywczas.myworkout.watch.activities.trainingplan.day.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.ExerciseEntity
import javax.inject.Inject

class DayRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase,
) : DayRepository {

    override suspend fun getExercises(dayId: Long): List<DayElements.Exercise> = trainings.getExercises(dayId).map { it.toDomain() }

    private fun ExerciseEntity.toDomain() = DayElements.Exercise(
        id = id,
        name = name,
        sequence = sequence,
        isFinished = isFinished
    )

    override suspend fun getDayHeader(dayId: Long): DayElements.DayHeader = trainings.getDay(dayId).toDomain()

    private fun DayEntity.toDomain(): DayElements.DayHeader = DayElements.DayHeader(
        dateStarted = dateStarted,
        dateFinished = dateFinished
    )

    override suspend fun isCardioDone(dayId: Long): Boolean = trainings.isCardioDone(dayId)

    override suspend fun copyDayAndTrainings(dayId: Long) = trainings.copyDaysAndTrainings(dayId)

    override suspend fun addCardio(dayId: Long) = trainings.addCardio(dayId)

}