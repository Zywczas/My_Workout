package com.zywczas.myworkout.di

import android.app.Application
import com.zywczas.myworkout.BaseApp
import com.zywczas.myworkout.di.modules.ActivitiesModule
import com.zywczas.myworkout.di.modules.FragmentsModule
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
        FragmentsModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

}