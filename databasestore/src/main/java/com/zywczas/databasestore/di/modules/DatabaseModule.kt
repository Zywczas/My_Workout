package com.zywczas.databasestore.di.modules

import android.content.Context
import androidx.room.Room
import com.zywczas.databasestore.db.AppDatabase
import com.zywczas.databasestore.db.ExercisesTemplatesDatabase
import com.zywczas.databasestore.db.PlannedExercisesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.reflect.KParameter

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideExercisesTemplatesDatabase(context: Context): ExercisesTemplatesDatabase =
            Room.databaseBuilder(context, ExercisesTemplatesDatabase::class.java, "ExercisesTemplatesDatabase").build()

    @Provides
    @Singleton
    internal fun providePlannedExercisesDatabase(context: Context): PlannedExercisesDatabase =
        Room.databaseBuilder(context, PlannedExercisesDatabase::class.java, "PlannedExercisesDatabase").build()
//
//    @Provides
//    internal fun provideLeaveRequestDao(db: AppDatabase): LeaveRequestDao = db.leaveRequestDao()

}