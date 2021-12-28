package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Day")
data class DayEntity(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                     @ColumnInfo(name = "foreignWeekId") var foreignWeekId: Long = -1,
                     @ColumnInfo(name = "name") val name: String = "",
                     @ColumnInfo(name = "sequence") val sequence: Int = 0,
                     @ColumnInfo(name = "dateStarted") val dateStarted: DateTime? = null,
                     @ColumnInfo(name = "dateFinished") val dateFinished: DateTime? = null,
                     @ColumnInfo(name = "isFinished") var isFinished: Boolean = false,
                     @ColumnInfo(name = "timeStamp") var timeStamp: DateTime = DateTime())