package com.zywczas.databasestore.trainings.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.zywczas.databasestore.trainings.entities.DayLocal
import com.zywczas.databasestore.trainings.entities.ExerciseLocal

data class DayRelations(@Embedded val day: DayLocal = DayLocal(),

                        @Relation(parentColumn = "id",
                                  entityColumn = "foreignDayId")
                        val exercises: List<ExerciseLocal> = emptyList()
)