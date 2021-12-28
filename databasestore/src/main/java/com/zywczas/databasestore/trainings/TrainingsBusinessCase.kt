package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.ExerciseEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity

interface TrainingsBusinessCase {
    //todo poprawic i tam gdzie jest zapis do bazy to wracac id nowego wiersza
    suspend fun getWeeks(): List<WeekEntity>

    suspend fun saveNewWeek(name: String)

    suspend fun getWeek(id: Long): WeekEntity

    suspend fun getDays(weekId: Long): List<DayEntity>

    suspend fun saveDay(day: DayEntity)

    suspend fun copyWeekAndTrainings(weekId: Long)

    suspend fun getExercises(dayId: Long): List<ExerciseEntity>

    suspend fun getDay(id: Long): DayEntity

    suspend fun saveNewExercise(dayId: Long, name: String, sets: Int, reps: String, weight: Double)

    suspend fun copyDaysAndTrainings(dayId: Long)

    suspend fun getWeekByExerciseId(exerciseId: Long): WeekEntity

    suspend fun getExercise(id: Long): ExerciseEntity

    suspend fun saveExercise(exercise: ExerciseEntity): Long

    suspend fun markExerciseAsFinished(id: Long)

    suspend fun markDayAsFinished(id: Long)

    suspend fun markWeekAsFinished(id: Long)

}