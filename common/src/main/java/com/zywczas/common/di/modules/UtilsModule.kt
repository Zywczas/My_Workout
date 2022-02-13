package com.zywczas.common.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Provides
    @WelcomeScreenDelay
    fun provideWelcomeScreenDelay() = 1500L

    @Qualifier
    annotation class WelcomeScreenDelay

}