package com.zywczas.databasestore.di.modules

import com.zywczas.databasestore.synchronisation.SynchronisationBusinessCase
import com.zywczas.databasestore.synchronisation.SynchronisationBusinessCaseImpl
import com.zywczas.databasestore.timer.TimerBusinessCase
import com.zywczas.databasestore.timer.TimerBusinessCaseImpl
import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.TrainingsBusinessCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BusinessCaseModule {

    @Binds
    internal abstract fun bindTrainingsBusinessCase(case: TrainingsBusinessCaseImpl) : TrainingsBusinessCase

    @Binds
    internal abstract fun bindTimerBusinessCase(case: TimerBusinessCaseImpl) : TimerBusinessCase

    @Binds
    internal abstract fun bindSynchronisationBusinessCase(case: SynchronisationBusinessCaseImpl) : SynchronisationBusinessCase

}