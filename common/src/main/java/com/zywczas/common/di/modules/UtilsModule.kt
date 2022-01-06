package com.zywczas.common.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class UtilsModule {

    @Provides
    @WelcomeScreenDelay
    fun provideWelcomeScreenDelay() = 1500L

    @Qualifier
    annotation class WelcomeScreenDelay

}