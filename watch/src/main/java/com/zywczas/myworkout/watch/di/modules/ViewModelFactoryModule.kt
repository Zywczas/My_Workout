package com.zywczas.myworkout.watch.di.modules

import androidx.lifecycle.ViewModel
import com.zywczas.common.di.qualifiers.ViewModelKey
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

}