package com.zywczas.databasestore.synchronisation.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime
//todo pousuwac timestampty z innych entity
@Entity(tableName = "LastUpdate")
data class LastUpdateLocal(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Long = 1,
    @ColumnInfo(name = "timeStamp") val timeStamp: DateTime = DateTime()
)