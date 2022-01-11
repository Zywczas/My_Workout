package com.zywczas.databasestore.timer.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Timer")
data class TimerLocal(@PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Long = 1,
                      @ColumnInfo(name = "seconds") val seconds: Int = 60)