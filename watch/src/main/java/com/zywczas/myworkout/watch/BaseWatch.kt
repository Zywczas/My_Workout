package com.zywczas.myworkout.watch


import com.zywczas.myworkout.watch.di.DaggerWatchComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseWatch : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerWatchComponent.factory().create(this)

}