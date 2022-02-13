package com.zywczas.myworkout.di.modules

import androidx.fragment.app.Fragment
import com.zywczas.common.di.qualifiers.FragmentKey
import com.zywczas.myworkout.fragments.weekslist.presentation.WeeksListFragment
import com.zywczas.myworkout.fragments.welcome.presentation.WelcomeFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentsModule {

    @Binds
    @IntoMap
    @FragmentKey(WelcomeFragment::class)
    abstract fun bindWelcomeFragment(fragment: WelcomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(WeeksListFragment::class)
    abstract fun bindWeeksListFragment(fragment: WeeksListFragment): Fragment

}