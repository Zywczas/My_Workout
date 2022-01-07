package com.zywczas.myworkout.watch.services.timer

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.wear.ongoing.OngoingActivity
import androidx.wear.ongoing.Status
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.logD
import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation.TimerActivity
import com.zywczas.myworkout.watch.di.AppInjector
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class TimerService : LifecycleService() {

    private val TAG = "TimerService"

    private val PACKAGE_NAME = "com.zywczas.myworkout.watch"

    private val EXTRA_CANCEL_WORKOUT_FROM_NOTIFICATION = "$PACKAGE_NAME.extra.CANCEL_SUBSCRIPTION_FROM_NOTIFICATION"

    private val NOTIFICATION_ID = 12345678 //todo poprawic i dac do jakiegos wspolnego zbiornika

    private val NOTIFICATION_CHANNEL_ID = "my_workout_channel_01" //todo poprawic i dac do jakiegos wspolnego zbiornika

    @SuppressLint("FieldSiteTargetOnQualifierAnnotation")
    @field:[Inject DispatcherIO]
    lateinit var dispatcherIO: CoroutineDispatcher

    @Inject
    lateinit var repo: TimerServiceRepository

    @Inject
    lateinit var dateTime: DateTimeProvider

    private val localBinder = LocalBinder()
    private val notificationManager by lazy { getSystemService(NotificationManager::class.java) }
    private var countTimeJob: Job? = null

    private val _timeLeft = MutableLiveData<String>()
    val timeLeft: LiveData<String> = _timeLeft

    private val _isAlarmOff = MutableLiveData<Boolean>()
    val isAlarmOff: LiveData<Boolean> = _isAlarmOff

    //todo poustawiac funkcje po kolei
    override fun onCreate() {
        AppInjector.watchComponent!!.inject(this) //todo poprawic to zeby nie wystawiac komponentu
        super.onCreate()
        logD("onCreateService")
        startService(Intent(this, TimerService::class.java))//todo sprawdzic na moim zegarku czy przez to nie powoduje uruchomienia dwoch serwisow
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        logD("onStartCommand")
        lifecycleScope.launch(dispatcherIO) {
            startCountingTime(repo.getBreakPeriodInSeconds())
        }
        val cancelWorkoutFromNotification = intent?.getBooleanExtra(EXTRA_CANCEL_WORKOUT_FROM_NOTIFICATION, false) ?: false //todo sprawdzic jak to dziala i pewnie usunac
        if (cancelWorkoutFromNotification) {
            stopCountingTimeWithServiceShutdownOption(stopService = true)
        }
        // Tells the system not to recreate the service after it's been killed.
        return Service.START_NOT_STICKY
    }

    private fun startCountingTime(seconds: Int) {
        logD("zaczyna liczyc czas")
        countTimeJob = lifecycleScope.launch(dispatcherIO) {
            _timeLeft.postValue(dateTime.getTimerRepresentationOf(seconds))
            val oneSecond = 1000L
            delay(oneSecond)
            for (i: Int in seconds - 1 downTo 0) {
                logD("i = $i") //todo usunac jak juz bedzie dzialac
                _timeLeft.postValue(dateTime.getTimerRepresentationOf(i))
                if (i == 0) {
                    finishCounting()
                }
                delay(oneSecond) //todo zamienic na poprawny miernik czasu
            }
        }
    }

    private fun finishCounting() {
        logD("konczy liczyc czas")
        notForegroundService()
        bringActivityToFront()
        _isAlarmOff.postValue(true)
    }

    private fun bringActivityToFront() {
        logD("bringActivityToFront")
        val intent = Intent(this, TimerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    private fun stopCountingTimeWithServiceShutdownOption(stopService: Boolean) {
        logD("stopCountingTimeWithServiceShutdownOption()")
        countTimeJob?.cancel()

        lifecycleScope.launch { //todo do wylotu
//            val job: Job = setActiveWalkingWorkout(false)
            if (stopService) {
                // Waits until DataStore data is saved before shutting down service. //todo wyczyscic
//                job.join()
                stopSelf()
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        logD("onBindService")
        notForegroundService()
        return localBinder
    }

    private fun notForegroundService() {
        logD("stopForeground")
        stopForeground(true)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        logD("onRebind")
        notForegroundService()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        super.onUnbind(intent)
        logD("onUnbind")
        //Ensures onRebind() is called if TimerActivity (client) rebinds.
        return true
    }

    override fun onDestroy() {
        logD("onDestroy") //todo sprawdzic
        super.onDestroy()
    }

    fun goToForegroundService() {
        logD("startForeground")
        val notification = generateNotification("jakis tekst main") //todo poprawic
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun generateNotification(mainText: String): Notification {
        Log.d(TAG, "generateNotification")

        // Main steps for building a BIG_TEXT_STYLE notification:
        //      0. Get data
        //      1. Create Notification Channel for O+
        //      2. Build the BIG_TEXT_STYLE
        //      3. Set up Intent / Pending Intent for notification
        //      4. Build and issue the notification

        // 0. Get data (note, the main notification text comes from the parameter above).
        val titleText = "jakis tytul notyfikacji" //todo poprawic

        // 1. Create Notification Channel.
        val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, titleText, NotificationManager.IMPORTANCE_DEFAULT)

        // Adds NotificationChannel to system. Attempting to create an
        // existing notification channel with its original values performs
        // no operation, so it's safe to perform the below sequence. //todo pousuwac te komentarze
        notificationManager.createNotificationChannel(notificationChannel)

        // 2. Build the BIG_TEXT_STYLE.
        val bigTextStyle = NotificationCompat.BigTextStyle()
            .bigText(mainText)
            .setBigContentTitle(titleText)

        val launchActivityIntent = Intent(this, TimerActivity::class.java)

        val cancelIntent = Intent(this, TimerService::class.java).apply {
            putExtra(EXTRA_CANCEL_WORKOUT_FROM_NOTIFICATION, true)
        }

        val servicePendingIntent = PendingIntent.getService(this, 0, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT) //todo chyba do wylotu

        val activityPendingIntent = PendingIntent.getActivity(this, 0, launchActivityIntent, 0)

        // 4. Build and issue the notification.
        val notificationCompatBuilder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)

        val notificationBuilder = notificationCompatBuilder
            .setStyle(bigTextStyle)
            .setContentTitle(titleText)
            .setContentText(mainText)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            // Makes Notification an Ongoing Notification (a Notification with a background task).
            .setOngoing(true)
            // For an Ongoing Activity, used to decide priority on the watch face.
            .setCategory(NotificationCompat.CATEGORY_WORKOUT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(R.drawable.ic_walk, "uruchom aktywnosc", activityPendingIntent)
            .addAction(R.drawable.ic_close_circle, "zakoncz aktywnosc", servicePendingIntent) //todo pewnie do wylotu

        // TODO: Create an Ongoing Activity.
        val ongoingActivityStatus = Status.Builder()
            // Sets the text used across various surfaces.
            .addTemplate(mainText)
            .build()

        val ongoingActivity =
            OngoingActivity.Builder(applicationContext, NOTIFICATION_ID, notificationBuilder)
                // Sets icon that will appear on the watch face in active mode. If it isn't set,
                // the watch face will use the static icon in active mode.
                .setAnimatedIcon(R.drawable.animated_walk)
                // Sets the icon that will appear on the watch face in ambient mode.
                // Falls back to Notification's smallIcon if not set. If neither is set,
                // an Exception is thrown.
                .setStaticIcon(R.drawable.ic_walk)
                // Sets the tap/touch event, so users can re-enter your app from the
                // other surfaces.
                // Falls back to Notification's contentIntent if not set. If neither is set,
                // an Exception is thrown.
                .setTouchIntent(activityPendingIntent)
                // In our case, sets the text used for the Ongoing Activity (more options are
                // available for timers and stop watches).
                .setStatus(ongoingActivityStatus)
                .build()

        // Applies any Ongoing Activity updates to the notification builder.
        // This method should always be called right before you build your notification,
        // since an Ongoing Activity doesn't hold references to the context.
        ongoingActivity.apply(applicationContext)

        return notificationBuilder.build()
    }

    /**
     * Class used for the client Binder.  Since this service runs in the same process as its
     * clients, we don't need to deal with IPC. todo sprawdzic co to IPC
     */
    inner class LocalBinder : Binder() {

        internal val timerService: TimerService = this@TimerService

    }

}