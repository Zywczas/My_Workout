package com.zywczas.myworkout.watch.di.modules

import androidx.lifecycle.ViewModel
import com.zywczas.common.di.qualifiers.ViewModelKey
import com.zywczas.myworkout.watch.activities.settings.main.presentation.SettingsMainViewModel
import com.zywczas.myworkout.watch.activities.settings.timer.presentation.SettingsTimerViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation.TimerViewModel
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
    @ViewModelKey(SettingsTimerViewModel::class)
    abstract fun bindSettingsTimerViewModel(vm: SettingsTimerViewModel) : ViewModel

}