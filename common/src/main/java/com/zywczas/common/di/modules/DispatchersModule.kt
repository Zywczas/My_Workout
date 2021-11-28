package com.zywczas.common.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
class DispatchersModule {

    @Provides
    @DispatcherIO
    fun provideDispatchersIO() : CoroutineDispatcher = Dispatchers.IO

    @Qualifier
    annotation class DispatcherIO

}