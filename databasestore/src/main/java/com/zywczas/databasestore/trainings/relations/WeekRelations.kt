package com.zywczas.databasestore.trainings.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity

data class WeekRelations(@Embedded val week: WeekEntity = WeekEntity(),

                         @Relation(parentColumn = "id",
                                   entityColumn = "foreignWeekId",
                                   entity = DayEntity::class)
                         val days: List<DayRelations> = emptyList())