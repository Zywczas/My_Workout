package com.zywczas.databasestore.timer.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Timer") //todo pozamieniac nazwy wszytkich na local, a funkcji na toLocal()
data class TimerEntity(@PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Long = 1,
                       @ColumnInfo(name = "seconds") val seconds: Int = 60)