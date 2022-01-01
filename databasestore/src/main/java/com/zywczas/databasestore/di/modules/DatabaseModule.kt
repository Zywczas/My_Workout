package com.zywczas.databasestore.di.modules

import android.content.Context
import androidx.room.Room
import com.zywczas.databasestore.db.TrainingsDatabase
import com.zywczas.databasestore.timer.dao.TimerDao
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideTrainingsDatabase(context: Context): TrainingsDatabase =
        Room.databaseBuilder(context, TrainingsDatabase::class.java, "TrainingsDatabase").build()

    @Provides
    internal fun provideTimerDao(db: TrainingsDatabase): TimerDao = db.timerDao()

    @Provides
    internal fun provideDayDao(db: TrainingsDatabase): DayDao = db.dayDao()

    @Provides
    internal fun provideExerciseDao(db: TrainingsDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    internal fun provideWeekDao(db: TrainingsDatabase): WeekDao = db.weekDao()

}