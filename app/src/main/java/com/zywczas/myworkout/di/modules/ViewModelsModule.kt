package com.zywczas.myworkout.di.modules

import androidx.lifecycle.ViewModel
import com.zywczas.common.di.qualifiers.ViewModelKey
import com.zywczas.myworkout.fragments.weekslist.presentation.WeeksListViewModel
import com.zywczas.myworkout.fragments.welcome.presentation.WelcomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    abstract fun bindWelcomeViewModel(vm: WelcomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeeksListViewModel::class)
    abstract fun bindWeeksListViewModel(vm: WeeksListViewModel) : ViewModel

}