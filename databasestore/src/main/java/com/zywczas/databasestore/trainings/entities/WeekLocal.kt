package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Week")
data class WeekLocal(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                     @ColumnInfo(name = "name") val name: String = "",
                     @ColumnInfo(name = "sequence") val sequence: Int = 0,
                     @ColumnInfo(name = "copyVersion") val copyVersion: Int = 1,
                     @ColumnInfo(name = "dateStarted") var dateStarted: DateTime? = null,
                     @ColumnInfo(name = "dateFinished") var dateFinished: DateTime? = null)