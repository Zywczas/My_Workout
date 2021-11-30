package com.zywczas.myworkout.watch.di

import android.app.Application
import com.zywczas.common.di.modules.CommonBinderModule
import com.zywczas.common.di.modules.CommonProviderModule
import com.zywczas.common.di.modules.DispatchersModule
import com.zywczas.databasestore.di.modules.DatabaseModule
import com.zywczas.myworkout.watch.BaseWatch
import com.zywczas.myworkout.watch.di.modules.ActivityBuilderModule
import com.zywczas.myworkout.watch.di.modules.AppModule
import com.zywczas.myworkout.watch.di.modules.DomainModule
import com.zywczas.myworkout.watch.di.modules.FragmentFactoryModule
import com.zywczas.myworkout.watch.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    DispatchersModule::class,
    DomainModule::class,
    ActivityBuilderModule::class,
    FragmentFactoryModule::class,
    ViewModelFactoryModule::class,
    DatabaseModule::class,
    CommonBinderModule::class,
    CommonProviderModule::class
])
interface WatchComponent : AndroidInjector<BaseWatch> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): WatchComponent
    }

}