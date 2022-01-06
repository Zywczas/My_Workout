package com.zywczas.databasestore.trainings.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.zywczas.databasestore.trainings.entities.DayLocal
import com.zywczas.databasestore.trainings.entities.WeekLocal

data class WeekRelations(@Embedded val week: WeekLocal = WeekLocal(),

                         @Relation(parentColumn = "id",
                                   entityColumn = "foreignWeekId",
                                   entity = DayLocal::class)
                         val days: List<DayRelations> = emptyList())