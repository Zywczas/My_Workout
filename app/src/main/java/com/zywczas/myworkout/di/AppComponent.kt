package com.zywczas.myworkout.di

import android.app.Application
import com.zywczas.myworkout.BaseApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [

])
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

}