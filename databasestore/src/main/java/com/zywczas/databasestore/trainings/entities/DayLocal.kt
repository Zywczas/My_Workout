package com.zywczas.databasestore.trainings.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Day")
data class DayLocal(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
                    @ColumnInfo(name = "foreignWeekId") var foreignWeekId: Long = -1,
                    @ColumnInfo(name = "name") val name: String = "",
                    @ColumnInfo(name = "sequence") val sequence: Int = 0,
                    @ColumnInfo(name = "dateStarted") var dateStarted: DateTime? = null,
                    @ColumnInfo(name = "dateFinished") var dateFinished: DateTime? = null,
                    @ColumnInfo(name = "isCardioDone") var isCardioDone: Boolean = false)