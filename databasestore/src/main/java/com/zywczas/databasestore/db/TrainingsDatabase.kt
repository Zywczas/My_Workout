package com.zywczas.databasestore.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zywczas.databasestore.trainings.entities.Week
import com.zywczas.databasestore.utils.Converters

@Database(
        entities = [
            Week::class
        ],
        version = 1
)
@TypeConverters(Converters::class)
internal abstract class TrainingsDatabase : RoomDatabase() {

//    abstract fun leaveRequestDao(): LeaveRequestDao

}