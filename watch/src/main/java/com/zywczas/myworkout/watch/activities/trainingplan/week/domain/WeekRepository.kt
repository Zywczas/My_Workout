package com.zywczas.myworkout.watch.activities.trainingplan.week.domain

interface WeekRepository {

    suspend fun getWeek(id: Long): DaysElements.WeekHeader

    suspend fun getDays(weekId: Long): List<DaysElements.Day>

    suspend fun saveNewDay(name: String, weekId: Long, sequence: Int)

}