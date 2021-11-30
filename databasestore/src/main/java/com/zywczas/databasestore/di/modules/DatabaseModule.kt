package com.zywczas.databasestore.di.modules

import android.content.Context
import androidx.room.Room
import com.zywczas.databasestore.db.PlannedTrainingsDatabase
import com.zywczas.databasestore.db.TrainingsTemplatesDatabase
import com.zywczas.databasestore.trainings.dao.CardioDao
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.TimerDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideTrainingsTemplatesDatabase(context: Context): TrainingsTemplatesDatabase =
            Room.databaseBuilder(context, TrainingsTemplatesDatabase::class.java, "TrainingsTemplates").build()

    @Provides
    @Singleton
    internal fun providePlannedTrainingsDatabase(context: Context): PlannedTrainingsDatabase =
        Room.databaseBuilder(context, PlannedTrainingsDatabase::class.java, "PlannedTrainings").build()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesCardioDao(db: TrainingsTemplatesDatabase): CardioDao = db.cardioDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedCardioDao(db: PlannedTrainingsDatabase): CardioDao = db.cardioDao()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesDayDao(db: TrainingsTemplatesDatabase): DayDao = db.dayDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedDayDao(db: PlannedTrainingsDatabase): DayDao = db.dayDao()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesExerciseDao(db: TrainingsTemplatesDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedExerciseDao(db: PlannedTrainingsDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesTimerDao(db: TrainingsTemplatesDatabase): TimerDao = db.timerDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedTimerDao(db: PlannedTrainingsDatabase): TimerDao = db.timerDao()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesWeekDao(db: TrainingsTemplatesDatabase): WeekDao = db.weekDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedWeekDao(db: PlannedTrainingsDatabase): WeekDao = db.weekDao()

    @Qualifier
    annotation class PlannedTrainings

    @Qualifier
    annotation class TrainingTemplates

}