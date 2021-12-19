package com.zywczas.myworkout.watch.activities.trainingplan.week.domain

interface WeekRepository {

    suspend fun getWeek(id: Long): WeekElements.WeekHeader

    suspend fun getDays(weekId: Long): List<WeekElements.Day>

    suspend fun saveNewDay(name: String, weekId: Long, sequence: Int)

    suspend fun copyWeekAndTrainings(weekId: Long)

}