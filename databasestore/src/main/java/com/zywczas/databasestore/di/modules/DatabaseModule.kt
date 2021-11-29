package com.zywczas.databasestore.di.modules

import android.content.Context
import androidx.room.Room
import com.zywczas.databasestore.db.PlannedTrainingsDatabase
import com.zywczas.databasestore.db.TrainingsTemplatesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideTrainingsTemplatesDatabase(context: Context): TrainingsTemplatesDatabase =
            Room.databaseBuilder(context, TrainingsTemplatesDatabase::class.java, "TrainingsTemplatesDatabase").build()

    @Provides
    @Singleton
    internal fun providePlannedTrainingsDatabase(context: Context): PlannedTrainingsDatabase =
        Room.databaseBuilder(context, PlannedTrainingsDatabase::class.java, "PlannedTrainingsDatabase").build()
//
//    @Provides
//    internal fun provideLeaveRequestDao(db: TrainingsDatabase): LeaveRequestDao = db.leaveRequestDao()

}