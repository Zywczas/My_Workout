package com.zywczas.databasestore.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zywczas.databasestore.timer.dao.TimerDao
import com.zywczas.databasestore.timer.entities.TimerEntity
import com.zywczas.databasestore.utils.Converters

@Database(
    entities = [
        TimerEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
internal abstract class GeneralDatabase : RoomDatabase() {

    abstract fun timerDao(): TimerDao

}