package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cardio")
data class CardioEntity(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                        @ColumnInfo(name = "foreignDayId") val foreignDayId: Long = -1,
                        @ColumnInfo(name = "name") val name: String = "",
                        @ColumnInfo(name = "sequence") val sequence: Int = 0,
                        @ColumnInfo(name = "minutes") val minutes: Int = 0,
                        @ColumnInfo(name = "level") val level: Double = 0.0,
                        @ColumnInfo(name = "isFinished") val isFinished: Boolean = false)