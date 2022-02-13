package com.zywczas.myworkout.di

import android.app.Application
import com.zywczas.common.di.modules.CommonBinderModule
import com.zywczas.common.di.modules.DispatchersModule
import com.zywczas.common.di.modules.UtilsModule
import com.zywczas.myworkout.BaseApp
import com.zywczas.myworkout.di.modules.ActivitiesModule
import com.zywczas.myworkout.di.modules.FragmentsModule
import com.zywczas.myworkout.di.modules.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        FragmentsModule::class,
        ViewModelsModule::class,
        DispatchersModule::class,
        CommonBinderModule::class,
        UtilsModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

}