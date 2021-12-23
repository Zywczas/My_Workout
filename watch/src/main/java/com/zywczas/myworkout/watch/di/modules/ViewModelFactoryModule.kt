package com.zywczas.myworkout.watch.di.modules

import androidx.lifecycle.ViewModel
import com.zywczas.common.di.qualifiers.ViewModelKey
import com.zywczas.myworkout.watch.activities.settings.main.presentation.SettingsMainViewModel
import com.zywczas.myworkout.watch.activities.settings.timer.presentation.SettingsTimerViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.addexercise.presentation.AddExerciseViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.day.presentation.DayViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation.ExerciseViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation.TimerViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.week.presentation.WeekViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation.WeeksListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeeksListViewModel::class)
    abstract fun bindWelcomeViewModel(vm: WeeksListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsMainViewModel::class)
    abstract fun bindSettingsMainViewModel(vm: SettingsMainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TimerViewModel::class)
    abstract fun bindTimerViewModel(vm: TimerViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeekViewModel::class)
    abstract fun bindWeekViewModel(vm: WeekViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsTimerViewModel::class)
    abstract fun bindSettingsTimerViewModel(vm: SettingsTimerViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DayViewModel::class)
    abstract fun bindDayViewModel(vm: DayViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddExerciseViewModel::class)
    abstract fun bindAddExerciseViewModel(vm: AddExerciseViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseViewModel::class)
    abstract fun bindExerciseViewModel(vm: ExerciseViewModel) : ViewModel

}