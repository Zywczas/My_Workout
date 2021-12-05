package com.zywczas.myworkout.watch.di.modules

import com.zywczas.myworkout.watch.activities.settings.timer.domain.SettingsTimerRepository
import com.zywczas.myworkout.watch.activities.settings.timer.domain.SettingsTimerRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.TimerRepository
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.TimerRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepository
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindWeekListRepository(repo: WeeksListRepositoryImpl): WeeksListRepository

    @Binds
    abstract fun bindSettingsTimerRepository(repo: SettingsTimerRepositoryImpl): SettingsTimerRepository

    @Binds
    abstract fun bindTimerRepository(repo: TimerRepositoryImpl): TimerRepository

}