package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity

interface TrainingsBusinessCase {

    suspend fun getWeeks(): List<WeekEntity>

    suspend fun saveNewWeek(week: WeekEntity)

    suspend fun getWeek(id: Long): WeekEntity

    suspend fun getDays(weekId: Long): List<DayEntity>

}