package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Timer")
data class Timer(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                 @ColumnInfo(name = "seconds") val seconds: Int)