package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.relations.WeekRelations

interface TrainingTemplatesBusinessCase {

    suspend fun getWeekRelationsList(): List<WeekRelations>

}