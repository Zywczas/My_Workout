package com.zywczas.databasestore.db

import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zywczas.databasestore.synchronisation.dao.LastUpdateDao
import com.zywczas.databasestore.synchronisation.entities.LastUpdateLocal
import com.zywczas.databasestore.timer.dao.TimerDao
import com.zywczas.databasestore.timer.entities.TimerLocal
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import com.zywczas.databasestore.trainings.entities.DayLocal
import com.zywczas.databasestore.trainings.entities.ExerciseLocal
import com.zywczas.databasestore.trainings.entities.WeekLocal
import com.zywczas.databasestore.utils.Converters

@Database(
    entities = [
        WeekLocal::class,
        DayLocal::class,
        ExerciseLocal::class,
        LastUpdateLocal::class,
        TimerLocal::class],
    version = 1
)
@TypeConverters(Converters::class)
@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
abstract class TrainingsDatabase : RoomDatabase() {

    abstract fun dayDao(): DayDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun weekDao(): WeekDao
    internal abstract fun timerDao(): TimerDao
    internal abstract fun lastUpdateDao(): LastUpdateDao
}
