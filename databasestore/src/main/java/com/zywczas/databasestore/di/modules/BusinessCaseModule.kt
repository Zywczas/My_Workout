package com.zywczas.databasestore.di.modules

import com.zywczas.databasestore.timer.TimerBusinessCase
import com.zywczas.databasestore.timer.TimerBusinessCaseImpl
import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.TrainingsBusinessCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class BusinessCaseModule {

    @Binds
    internal abstract fun bindPlannedTrainingsBusinessCase(case: PlannedTrainingsBusinessCaseImpl) : PlannedTrainingsBusinessCase

    @Binds
    internal abstract fun bindTrainingTemplatesBusinessCase(case: TrainingsBusinessCaseImpl) : TrainingsBusinessCase

    @Binds
    internal abstract fun bindTimerBusinessCase(case: TimerBusinessCaseImpl) : TimerBusinessCase

}