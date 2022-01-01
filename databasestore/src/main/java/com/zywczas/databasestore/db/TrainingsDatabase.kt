package com.zywczas.databasestore.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zywczas.databasestore.synchronisation.dao.LastUpdateDao
import com.zywczas.databasestore.synchronisation.entities.LastUpdateLocal
import com.zywczas.databasestore.timer.dao.TimerDao
import com.zywczas.databasestore.timer.entities.TimerEntity
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.ExerciseEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity
import com.zywczas.databasestore.utils.Converters

@Database(
        entities = [
            WeekEntity::class,
            DayEntity::class,
            ExerciseEntity::class,
            LastUpdateLocal::class,
            TimerEntity::class],
        version = 1
)
@TypeConverters(Converters::class)
internal abstract class TrainingsDatabase : RoomDatabase() {

    abstract fun dayDao(): DayDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun weekDao(): WeekDao
    abstract fun timerDao(): TimerDao
    abstract fun lastUpdateDao(): LastUpdateDao

}