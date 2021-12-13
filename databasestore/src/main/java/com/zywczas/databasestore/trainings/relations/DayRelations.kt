package com.zywczas.databasestore.trainings.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.zywczas.databasestore.trainings.entities.CardioEntity
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.ExerciseEntity
//todo nie wiem czy potrzebne
data class DayRelations(@Embedded val day: DayEntity = DayEntity(),

                        @Relation(parentColumn = "id",
                                  entityColumn = "foreignDayId")
                        val exercises: List<ExerciseEntity> = emptyList(),

                        @Relation(parentColumn = "id",
                                  entityColumn = "foreignDayId")
                        val cardios: CardioEntity? = null)