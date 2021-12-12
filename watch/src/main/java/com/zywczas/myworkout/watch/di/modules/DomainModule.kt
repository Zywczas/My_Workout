package com.zywczas.myworkout.watch.di.modules

import com.zywczas.myworkout.watch.activities.settings.timer.domain.SettingsTimerRepository
import com.zywczas.myworkout.watch.activities.settings.timer.domain.SettingsTimerRepositoryImpl
import com.zywczas.myworkout.watch.activities.settings.weeks.domain.SettingsWeeksRepository
import com.zywczas.myworkout.watch.activities.settings.weeks.domain.SettingsWeeksRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.TimerRepository
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.TimerRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepository
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepositoryImpl
import com.zywczas.myworkout.watch.services.timer.TimerServiceRepository
import com.zywczas.myworkout.watch.services.timer.TimerServiceRepositoryImpl
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

    @Binds
    abstract fun bindTimerServiceRepository(repo: TimerServiceRepositoryImpl): TimerServiceRepository

    @Binds
    abstract fun bindSettingsWeeksRepository(repo: SettingsWeeksRepositoryImpl): SettingsWeeksRepository

}