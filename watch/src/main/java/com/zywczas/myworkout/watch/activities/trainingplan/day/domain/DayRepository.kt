package com.zywczas.myworkout.watch.activities.trainingplan.day.domain

interface DayRepository {

    suspend fun getExercises(dayId: Long): List<DayElements.Exercise>

    suspend fun getDayHeader(dayId: Long): DayElements.DayHeader

    suspend fun copyDayAndTrainings(dayId: Long)

}