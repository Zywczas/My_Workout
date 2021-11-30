package com.zywczas.myworkout.watch


import android.app.Application
import com.zywczas.myworkout.watch.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector

class BaseWatch : Application() {

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = //todo usunac
//        DaggerWatchComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

}