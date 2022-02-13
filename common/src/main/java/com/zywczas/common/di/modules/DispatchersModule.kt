package com.zywczas.common.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    @DispatcherIO
    fun provideDispatchersIO() : CoroutineDispatcher = Dispatchers.IO

    @Qualifier
    annotation class DispatcherIO

}