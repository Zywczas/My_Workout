package com.zywczas.myworkout.watch.di

import com.zywczas.common.di.modules.CommonBinderModule
import com.zywczas.common.di.modules.DispatchersModule
import com.zywczas.common.di.modules.UtilsModule
import com.zywczas.databasestore.di.modules.BusinessCaseModule
import com.zywczas.databasestore.di.modules.DatabaseModule
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.di.modules.AppModule
import com.zywczas.myworkout.watch.di.modules.DomainModule
import com.zywczas.myworkout.watch.di.modules.ViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DispatchersModule::class,
    DomainModule::class,
    ViewModelFactoryModule::class,
    DatabaseModule::class,
    BusinessCaseModule::class,
    CommonBinderModule::class,
    UtilsModule::class
])
interface WatchComponent {

    fun inject(activity: BaseActivity)

}