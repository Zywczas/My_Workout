package com.zywczas.myworkout.watch.di.modules

import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepository
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindWeekListRepository(repo: WeeksListRepositoryImpl): WeeksListRepository

}