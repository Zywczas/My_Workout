package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.entities.DayLocal
import com.zywczas.databasestore.trainings.entities.ExerciseLocal
import com.zywczas.databasestore.trainings.entities.WeekLocal

interface TrainingsBusinessCase {

    suspend fun getWeeks(): List<WeekLocal>

    suspend fun saveNewWeek(name: String)

    suspend fun getWeek(id: Long): WeekLocal

    suspend fun getDays(weekId: Long): List<DayLocal>

    suspend fun saveDay(day: DayLocal)

    suspend fun copyWeekAndTrainings(weekId: Long)

    suspend fun getExercises(dayId: Long): List<ExerciseLocal>

    suspend fun getDay(id: Long): DayLocal

    suspend fun saveNewExercise(dayId: Long, name: String, sets: Int, reps: String, weight: Double)

    suspend fun copyDayAndTrainingsInTheSameWeek(dayId: Long)

    suspend fun getWeekByExerciseId(exerciseId: Long): WeekLocal

    suspend fun getExercise(id: Long): ExerciseLocal

    suspend fun saveExercise(exercise: ExerciseLocal)

    suspend fun markExerciseAsFinished(id: Long)

    suspend fun markDayAsStarted(id: Long)

    suspend fun markWeekAsStartedIfNotStarted(dayId: Long)

    suspend fun markDayAsFinished(id: Long)

    suspend fun markWeekAsFinished(id: Long)

    suspend fun saveWeight(exerciseId: Long, weight: Double)

    suspend fun isCardioDone(dayId: Long): Boolean

    suspend fun addCardio(dayId: Long)

    suspend fun deleteExercise(id: Long)

    suspend fun deleteDay(id: Long)

    suspend fun deleteWeek(id: Long)

    suspend fun getWeekId(dayId: Long): Long

    suspend fun isDayStarted(id: Long): Boolean

}