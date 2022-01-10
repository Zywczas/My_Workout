package com.zywczas.myworkout.watch.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.di.modules.AppModule

object AppInjector {

    private var watchComponent: WatchComponent? = null

    fun init(app: Application){
        buildAppComponent(app)
        app.registerActivityLifecycleCallbacks(activityInjector)
    }

    private fun buildAppComponent(context: Context) {
        watchComponent = DaggerWatchComponent.builder()
            .appModule(AppModule(context))
            .build()
    }

    private val activityInjector = object : Application.ActivityLifecycleCallbacks{
        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            if (activity is BaseActivity) {
                watchComponent!!.inject(activity)
            }
        }

        override fun onActivityDestroyed(activity: Activity) {}
        override fun onActivityStarted(p0: Activity) {}
        override fun onActivityResumed(p0: Activity) {}
        override fun onActivityPaused(p0: Activity) {}
        override fun onActivityStopped(p0: Activity) {}
        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
    }

}