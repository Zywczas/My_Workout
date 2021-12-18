package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity
import com.zywczas.databasestore.trainings.relations.WeekRelations

interface TrainingsBusinessCase {

    suspend fun getWeeks(): List<WeekEntity>

    suspend fun findNextWeekPosition(): Int

    suspend fun saveNewWeek(week: WeekEntity)

    suspend fun getWeek(id: Long): WeekEntity

    suspend fun getDays(weekId: Long): List<DayEntity>

    suspend fun saveNewDay(day: DayEntity)

    suspend fun copyWeekAndTrainings(weekId: Long)

}