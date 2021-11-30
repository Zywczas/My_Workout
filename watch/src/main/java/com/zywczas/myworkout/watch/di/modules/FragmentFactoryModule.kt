package com.zywczas.myworkout.watch.di.modules

import androidx.fragment.app.Fragment
import com.zywczas.common.di.qualifiers.FragmentKey
import com.zywczas.myworkout.watch.fragments.welcome.presentation.WelcomeFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentFactoryModule {

    @Binds
    @IntoMap
    @FragmentKey(WelcomeFragment::class)
    abstract fun bindWelcomeFragment(fragment: WelcomeFragment): Fragment

}