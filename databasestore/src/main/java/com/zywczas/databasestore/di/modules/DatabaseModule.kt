package com.zywczas.databasestore.di.modules

import android.content.Context
import androidx.room.Room
import com.zywczas.databasestore.db.TrainingsDatabase
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
    @TrainingTemplates
    internal fun provideTrainingsTemplatesDatabase(context: Context): TrainingsDatabase =
        Room.databaseBuilder(context, TrainingsDatabase::class.java, "TrainingsTemplatesDatabase").build()

    @Provides
    @Singleton
    @PlannedTrainings
    internal fun providePlannedTrainingsDatabase(context: Context): TrainingsDatabase =
        Room.databaseBuilder(context, TrainingsDatabase::class.java, "PlannedTrainingsDatabase").build()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesCardioDao(@TrainingTemplates db: TrainingsDatabase): CardioDao = db.cardioDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedCardioDao(@PlannedTrainings db: TrainingsDatabase): CardioDao = db.cardioDao()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesDayDao(@TrainingTemplates db: TrainingsDatabase): DayDao = db.dayDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedDayDao(@PlannedTrainings db: TrainingsDatabase): DayDao = db.dayDao()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesExerciseDao(@TrainingTemplates db: TrainingsDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedExerciseDao(@PlannedTrainings db: TrainingsDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesTimerDao(@TrainingTemplates db: TrainingsDatabase): TimerDao = db.timerDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedTimerDao(@PlannedTrainings db: TrainingsDatabase): TimerDao = db.timerDao()

    @Provides
    @TrainingTemplates
    internal fun provideTemplatesWeekDao(@TrainingTemplates db: TrainingsDatabase): WeekDao = db.weekDao()

    @Provides
    @PlannedTrainings
    internal fun providePlannedWeekDao(@PlannedTrainings db: TrainingsDatabase): WeekDao = db.weekDao()

    @Qualifier
    annotation class PlannedTrainings

    @Qualifier
    annotation class TrainingTemplates

}