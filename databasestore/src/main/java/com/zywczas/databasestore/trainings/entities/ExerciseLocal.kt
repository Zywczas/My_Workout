package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise")
data class ExerciseLocal(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                         @ColumnInfo(name = "foreignDayId") var foreignDayId: Long = -1,
                         @ColumnInfo(name = "name") val name: String = "",
                         @ColumnInfo(name = "sequence") val sequence: Int = 0,
                         @ColumnInfo(name = "setsQuantity") val setsQuantity: Int = 0,
                         @ColumnInfo(name = "currentSet") val currentSet: Int = 1,
                         @ColumnInfo(name = "repsQuantity") val repsQuantity: String = "",
                         @ColumnInfo(name = "weight") var weight: Double = 0.0,
                         @ColumnInfo(name = "isFinished") var isFinished: Boolean = false)