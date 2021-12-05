package com.zywczas.myworkout.watch.services.timer

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.zywczas.common.extetions.logD

class TimerService : LifecycleService() {

    private val localBinder = LocalBinder()

    val liveData = MutableLiveData<Int>()

    private var configurationChange = false
    private var serviceRunningInForeground = false

    override fun onCreate() {
        super.onCreate()
        logD("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        logD("onStartCommand")
        // Tells the system not to recreate the service after it's been killed.
        return Service.START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        logD("onBindService")
        notForegroundService()
        return localBinder
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        logD("onRebind")
        notForegroundService()
    }

    private fun notForegroundService() {
        stopForeground(true)
        serviceRunningInForeground = false
        configurationChange = false
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    /**
     * Class used for the client Binder.  Since this service runs in the same process as its
     * clients, we don't need to deal with IPC. todo sprawdzic co to IPC
     */
    inner class LocalBinder : Binder() {

        internal val timerService: TimerService = this@TimerService

    }

    companion object {
        private const val TAG = "TimerService"

        private const val PACKAGE_NAME = "com.zywczas.myworkout.watch"

//        private const val EXTRA_CANCEL_WORKOUT_FROM_NOTIFICATION =
//            "$PACKAGE_NAME.extra.CANCEL_SUBSCRIPTION_FROM_NOTIFICATION"

//        private const val NOTIFICATION_ID = 12345678

//        private const val NOTIFICATION_CHANNEL_ID = "my_workout_channel_01"
    }

}