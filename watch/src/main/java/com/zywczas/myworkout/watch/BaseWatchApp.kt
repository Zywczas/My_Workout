package com.zywczas.myworkout.watch


import android.app.Application
import com.zywczas.myworkout.watch.di.AppInjector

class BaseWatchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

}