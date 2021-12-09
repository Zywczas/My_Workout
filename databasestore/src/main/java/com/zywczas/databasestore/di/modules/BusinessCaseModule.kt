package com.zywczas.databasestore.di.modules

import com.zywczas.databasestore.timer.TimerBusinessCase
import com.zywczas.databasestore.timer.TimerBusinessCaseImpl
import com.zywczas.databasestore.trainings.PlannedTrainingsBusinessCase
import com.zywczas.databasestore.trainings.PlannedTrainingsBusinessCaseImpl
import com.zywczas.databasestore.trainings.TrainingTemplatesBusinessCase
import com.zywczas.databasestore.trainings.TrainingTemplatesBusinessCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class BusinessCaseModule {

    @Binds
    internal abstract fun bindPlannedTrainingsBusinessCase(case: PlannedTrainingsBusinessCaseImpl) : PlannedTrainingsBusinessCase

    @Binds
    internal abstract fun bindTrainingTemplatesBusinessCase(case: TrainingTemplatesBusinessCaseImpl) : TrainingTemplatesBusinessCase

    @Binds
    internal abstract fun bindTimerBusinessCase(case: TimerBusinessCaseImpl) : TimerBusinessCase

}