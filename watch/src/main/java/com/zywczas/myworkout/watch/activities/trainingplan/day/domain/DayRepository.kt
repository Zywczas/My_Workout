package com.zywczas.myworkout.watch.activities.trainingplan.day.domain

interface DayRepository {

    suspend fun getExercises(dayId: Long): List<DayElements.Exercise>

    suspend fun getDayHeader(dayId: Long): DayElements.DayHeader

    suspend fun isCardioDone(dayId: Long): Boolean

    suspend fun copyDayAndTrainings(dayId: Long)

    suspend fun addCardio(dayId: Long)

}