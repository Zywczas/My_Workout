package com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain

interface ExerciseRepository {

    suspend fun getExercise(id: Long): Exercise

    suspend fun getExercises(dayId: Long, weekId: Long): List<Exercise>

    suspend fun markExerciseAsFinished(id: Long)

    suspend fun markDayAsFinished(id: Long)

    suspend fun getDays(weekId: Long): List<Day>

    suspend fun markWeekAsFinished(id: Long)

}