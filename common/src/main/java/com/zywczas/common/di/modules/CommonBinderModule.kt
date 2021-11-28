package com.zywczas.common.di.modules

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.common.utils.DateTimeProviderImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CommonBinderModule {

    @Binds
    abstract fun bindDateTimeProvider(util: DateTimeProviderImpl): DateTimeProvider

}