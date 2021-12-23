package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.ExerciseEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity

interface TrainingsBusinessCase {

    suspend fun getWeeks(): List<WeekEntity>

    suspend fun saveNewWeek(name: String)

    suspend fun getWeek(id: Long): WeekEntity

    suspend fun getDays(weekId: Long): List<DayEntity>

    suspend fun saveDay(day: DayEntity)

    suspend fun copyWeekAndTrainings(weekId: Long)

    suspend fun getExercises(dayId: Long): List<ExerciseEntity>

    suspend fun getDay(id: Long): DayEntity

    suspend fun saveExercise(dayId: Long, name: String, sets: Int, reps: String, weight: Double)

    suspend fun copyDaysAndTrainings(dayId: Long)

}