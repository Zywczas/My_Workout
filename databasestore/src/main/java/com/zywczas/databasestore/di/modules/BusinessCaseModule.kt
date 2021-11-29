package com.zywczas.databasestore.di.modules

import com.zywczas.databasestore.trainings.PlannedTrainingsBusinessCase
import com.zywczas.databasestore.trainings.PlannedTrainingsBusinessCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class BusinessCaseModule {

    @Binds
    internal abstract fun bindPlannedTrainingsBusinessCase(case: PlannedTrainingsBusinessCaseImpl) : PlannedTrainingsBusinessCase

}