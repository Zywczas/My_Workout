package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Exercise")
data class ExerciseEntity(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                          @ColumnInfo(name = "foreignDayId") var foreignDayId: Long = -1,
                          @ColumnInfo(name = "name") val name: String = "",
                          @ColumnInfo(name = "sequence") val sequence: Int = 0,
                          @ColumnInfo(name = "setsQuantity") val setsQuantity: Int = 0,
                          @ColumnInfo(name = "repsQuantity") val repsQuantity: Int = 0,
                          @ColumnInfo(name = "weightInKg") val weightInKg: Double = 0.0,
                          @ColumnInfo(name = "isFinished") val isFinished: Boolean = false,
                          @ColumnInfo(name = "timeStamp") val timeStamp: DateTime = DateTime())