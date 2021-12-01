package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.entities.WeekEntity
import com.zywczas.databasestore.trainings.relations.WeekRelations

interface PlannedTrainingsBusinessCase {

    suspend fun getWeekRelationsList(): List<WeekRelations>
    suspend fun getWeeks(): List<WeekEntity>

}