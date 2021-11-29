package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cardio")
data class Cardio(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                  @ColumnInfo(name = "foreignDayId") val foreignDayId: Long = -1,
                  @ColumnInfo(name = "name") val name: String,
                  @ColumnInfo(name = "minutes") val minutes: Int,
                  @ColumnInfo(name = "level") val level: Double,
                  @ColumnInfo(name = "isFinished") val isFinished: Boolean)