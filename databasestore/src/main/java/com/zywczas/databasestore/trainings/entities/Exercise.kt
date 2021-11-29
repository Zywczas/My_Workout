package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise")
data class Exercise(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                    @ColumnInfo(name = "foreignDayId") val foreignDayId: Long = -1,
                    @ColumnInfo(name = "name") val name: String,
                    @ColumnInfo(name = "setsQuantity") val setsQuantity: Int,
                    @ColumnInfo(name = "repsQuantity") val repsQuantity: Int,
                    @ColumnInfo(name = "weightInKg") val weightInKg: Double,
                    @ColumnInfo(name = "isFinished") val isFinished: Boolean)