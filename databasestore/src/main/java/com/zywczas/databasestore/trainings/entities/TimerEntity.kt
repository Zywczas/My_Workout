package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Timer")
data class TimerEntity(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                       @ColumnInfo(name = "seconds") val seconds: Int = 0,
                       @ColumnInfo(name = "timeStamp") val timeStamp: DateTime = DateTime())