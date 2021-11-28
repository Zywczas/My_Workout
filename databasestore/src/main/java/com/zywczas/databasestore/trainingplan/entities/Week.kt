package com.zywczas.databasestore.trainingplan.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Week")
data class Week(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                @ColumnInfo(name = "name") val name: String,
                @ColumnInfo(name = "dateStarted") val dateStarted: DateTime?,
                @ColumnInfo(name = "dateFinished") val dateFinished: DateTime?,
                @ColumnInfo(name = "isFinished") val isFinished: Boolean)