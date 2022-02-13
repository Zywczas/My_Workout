package com.zywczas.common.di.modules

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.common.utils.DateTimeProviderImpl
import com.zywczas.common.utils.StringProvider
import com.zywczas.common.utils.StringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonBinderModule {

    @Binds
    abstract fun bindDateTimeProvider(util: DateTimeProviderImpl): DateTimeProvider

    @Binds
    abstract fun bindStringProvider(util: StringProviderImpl): StringProvider

}