package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime
//todo pozamieniac te entity na Local
@Entity(tableName = "Cardio")
data class CardioEntity(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                        @ColumnInfo(name = "foreignDayId") var foreignDayId: Long = -1,
                        @ColumnInfo(name = "name") val name: String = "",
                        @ColumnInfo(name = "minutes") val minutes: Int = 0,
                        @ColumnInfo(name = "level") val level: Double = 0.0,
                        @ColumnInfo(name = "isFinished") val isFinished: Boolean = false,
                        @ColumnInfo(name = "timeStamp") val timeStamp: DateTime = DateTime())