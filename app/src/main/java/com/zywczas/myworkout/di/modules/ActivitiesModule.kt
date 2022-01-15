package com.zywczas.myworkout.di.modules

import com.zywczas.myworkout.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

}