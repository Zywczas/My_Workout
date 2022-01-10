package com.zywczas.myworkout.watch.di.modules

import com.zywczas.myworkout.watch.activities.settings.timer.domain.SettingsTimerRepository
import com.zywczas.myworkout.watch.activities.settings.timer.domain.SettingsTimerRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.addexercise.domain.AddExerciseRepository
import com.zywczas.myworkout.watch.activities.trainingplan.addexercise.domain.AddExerciseRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.changeweight.domain.ChangeWeightRepository
import com.zywczas.myworkout.watch.activities.trainingplan.changeweight.domain.ChangeWeightRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.day.domain.DayRepository
import com.zywczas.myworkout.watch.activities.trainingplan.day.domain.DayRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain.ExerciseRepository
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain.ExerciseRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.TimerRepository
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.TimerRepositoryImpl
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.WeekRepository
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.WeekRepositoryImpl
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

    @Binds
    abstract fun bindWeekRepository(repo: WeekRepositoryImpl): WeekRepository

    @Binds
    abstract fun bindDayRepository(repo: DayRepositoryImpl): DayRepository

    @Binds
    abstract fun bindAddExerciseRepository(repo: AddExerciseRepositoryImpl): AddExerciseRepository

    @Binds
    abstract fun bindExerciseRepository(repo: ExerciseRepositoryImpl): ExerciseRepository

    @Binds
    abstract fun bindChangeWeightRepository(repo: ChangeWeightRepositoryImpl): ChangeWeightRepository

}