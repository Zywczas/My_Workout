package com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.DayLocal
import com.zywczas.databasestore.trainings.entities.ExerciseLocal
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : ExerciseRepository {

    override suspend fun getExercise(id: Long): Exercise {
        val week = trainings.getWeekByExerciseId(id)
        return trainings.getExercise(id).toDomain(week.id)
    }

    private fun ExerciseLocal.toDomain(weekId: Long) = Exercise(
        id = id,
        dayId = foreignDayId,
        weekId = weekId,
        name = name,
        sequence = sequence,
        setsQuantity = setsQuantity,
        currentSet = currentSet,
        repsQuantity = repsQuantity,
        weight = weight,
        isFinished = isFinished
    )

    override suspend fun getExercises(dayId: Long, weekId: Long): List<Exercise> {
        val week = trainings.getWeek(weekId)
        return trainings.getExercises(dayId).map { it.toDomain(week.id) }
    }

    override suspend fun markExerciseAsFinished(id: Long) {
        trainings.markExerciseAsFinished(id)
    }

    override suspend fun markDayAsFinished(id: Long) {
        trainings.markDayAsFinished(id)
    }

    override suspend fun getDays(weekId: Long): List<Day> = trainings.getDays(weekId).map { it.toDomain() }

    private fun DayLocal.toDomain() = Day(isFinished = dateFinished != null)

    override suspend fun markWeekAsFinished(id: Long) {
        trainings.markWeekAsFinished(id)
    }

    override suspend fun deleteExercise(id: Long) {
        trainings.deleteExercise(id)
    }

}