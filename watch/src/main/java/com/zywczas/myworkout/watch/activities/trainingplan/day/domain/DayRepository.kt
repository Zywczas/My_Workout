package com.zywczas.myworkout.watch.activities.trainingplan.day.domain

interface DayRepository {

    suspend fun getExercises(dayId: Long): List<DayElements.Exercise>

    suspend fun getDayHeader(dayId: Long): DayElements.DayHeader

    suspend fun isCardioDone(dayId: Long): Boolean

    suspend fun copyDayAndTrainings(dayId: Long)

    suspend fun addCardio(dayId: Long)

    suspend fun getWeekId(dayId: Long): Long

    suspend fun deleteDay(id: Long)

    suspend fun getDays(weekId: Long): List<Day>

    suspend fun markDayAsStarted(id: Long)

    suspend fun markWeekAsStartedIfNotStarted(dayId: Long)

    suspend fun markWeekAsFinished(id: Long)

}