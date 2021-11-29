package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Week")
data class WeekEntity(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                      @ColumnInfo(name = "name") val name: String = "",
                      @ColumnInfo(name = "sequence") val sequence: Int = 0,
                      @ColumnInfo(name = "dateStarted") val dateStarted: DateTime? = null,
                      @ColumnInfo(name = "dateFinished") val dateFinished: DateTime? = null,
                      @ColumnInfo(name = "isFinished") val isFinished: Boolean = false)