package com.zywczas.myworkout.di.modules

import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.WeeksListRepository
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.WeeksListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindWeekListRepository(repo: WeeksListRepositoryImpl): WeeksListRepository

}