package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Day")
data class Day(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
               @ColumnInfo(name = "foreignWeekId") val foreignWeekId: Long = -1,
               @ColumnInfo(name = "name") val name: String,
               @ColumnInfo(name = "dateFinished") val dateFinished: DateTime?,
               @ColumnInfo(name = "isFinished") val isFinished: Boolean)