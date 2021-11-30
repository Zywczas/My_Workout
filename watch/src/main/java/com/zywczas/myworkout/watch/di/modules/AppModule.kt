package com.zywczas.myworkout.watch.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideAppContext() : Context = context

}