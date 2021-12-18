package com.zywczas.myworkout.watch.activities.trainingplan.week.domain

import com.zywczas.databasestore.trainings.relations.WeekRelations

interface WeekRepository {

    suspend fun getWeek(id: Long): DaysElements.WeekHeader

    suspend fun getDays(weekId: Long): List<DaysElements.Day>

    suspend fun saveNewDay(name: String, weekId: Long, sequence: Int)

    suspend fun getWeekRelations(id: Long): WeekRelations

    suspend fun copyWeekAndTrainings(weekId: Long)

}