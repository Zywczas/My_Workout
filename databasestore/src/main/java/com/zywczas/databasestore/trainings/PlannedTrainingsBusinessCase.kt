package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.relations.WeekRelations

interface PlannedTrainingsBusinessCase {

    suspend fun getWeekRelationsList(): List<WeekRelations>

}