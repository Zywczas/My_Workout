package com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation

import android.app.ActivityManager
import android.content.*
import android.os.*
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.zywczas.common.extetions.logD
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.settings.timer.presentation.SettingsTimerActivity
import com.zywczas.myworkout.watch.activities.trainingplan.day.presentation.DayActivity
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation.ExerciseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.NextExercise
import com.zywczas.myworkout.watch.databinding.ActivityTimerBinding
import com.zywczas.myworkout.watch.services.timer.TimerService


class TimerActivity : BaseActivity() {

    init {
        logD("init")
    }

    private var binding: ActivityTimerBinding by autoRelease()
    private val viewModel: TimerViewModel by viewModels { viewModelFactory }
    private var timerService: TimerService? = null
    private var isTimerServiceBound = false
    private var isConfigurationChange = false
    private val vibrator by lazy { getSystemService(Vibrator::class.java) }
    private var isGoingToNextExercise = false
    private val exerciseId by lazy { intent.getLongExtra(DayActivity.KEY_EXERCISE_ID, 0L) }
    private val nextExerciseSet by lazy { intent.getIntExtra(ExerciseActivity.KEY_EXERCISE_SET, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isConfigurationChange = false //todo sprawdzic czy to jest potrzebne
        bindTimerService()
        viewModel.getExerciseDetails(exerciseId, nextExerciseSet)
        setupLiveDataObservers()
        setupOnClickListeners()
//        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, IntentFilter(TimerService.BROADCAST_BRING_APP_TO_FRONT))
    }

//    private val broadcastReceiver = object : BroadcastReceiver() { //todo poustawiac pokolei
//        override fun onReceive(context: Context, intent: Intent?) {
//            setTopApp(this@TimerActivity)
//        }
//    }

//    fun setTopApp(context: Context) {
//        val activityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
//            val list = activityManager.appTasks
//            for (appTask in list) {
//                logD("wynosze apke")
//                appTask.moveToFront()
//                break
//            }
//    }

    private fun bindTimerService(){ //todo dac jakies sprawdzenie gdzies czy ustawiony czas to nie jest zero albo 1, jezeli bedzie to jakos przeskakiwac timer service, moze dac sprawdzenie we wczesniejszej aktywnosci
        val serviceIntent = Intent(this, TimerService::class.java)
        bindService(serviceIntent, timerServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private val timerServiceConnection = object : ServiceConnection { //todo dac to nizej, tam gdzie uzywane i poustawiac wszystkie funkcje
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as? TimerService.LocalBinder
            timerService = binder?.timerService
            isTimerServiceBound = true
            setupServiceLiveDataObservers()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            timerService = null
            isTimerServiceBound = false
        }
    }

    private fun setupServiceLiveDataObservers(){
        timerService?.timeLeft?.observe(this){
            binding.timeLeft.text = it
        }
        timerService?.isAlarmOff?.observe(this) {
            if (it) {
                lifecycleScope.launchWhenResumed {
                    turnAlarmOn()
                    showFinishedCounter()
                }
            }
        }
    }

    private fun turnAlarmOn(){
        if (vibrator.hasVibrator()) {
            val breakBetweenVibrations = 1000L
            val vibrationLength = 1000L
            vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(breakBetweenVibrations, vibrationLength), 0))
        }
    }

    private fun showFinishedCounter(){
        binding.counterHeader.isVisible = false
        binding.exerciseLongDescriptionContainer.isVisible = false
        binding.nextSetPlaceholder.isVisible = false
        binding.nextSet.isVisible = false
        binding.goBack.isVisible = false
        binding.settings.isVisible = false
        binding.skipTimer.setText(R.string.nextExercise)
    }

    private fun setupLiveDataObservers(){
        viewModel.isExerciseLongDescriptionVisible.observe(this){ binding.exerciseLongDescriptionContainer.isVisible = it }
        viewModel.nextExercise.observe(this){ showExercise(it) }
        viewModel.nextExerciseId.observe(this){ goToExerciseActivityAndFinishThisActivity(it) }
    }

    private fun showExercise(exercise: NextExercise){
        binding.exerciseName.text = exercise.name
        binding.sets.text = exercise.setsQuantity.toString()
        binding.reps.text = exercise.repsQuantity
        binding.weight.text = exercise.weight.toString()
        binding.nextSet.text = exercise.nextSet.toString()
    }

    private fun goToExerciseActivityAndFinishThisActivity(exerciseId: Long){
        val intent = Intent(this, ExerciseActivity::class.java).apply {
            putExtra(DayActivity.KEY_EXERCISE_ID, exerciseId)
        }
        startActivity(intent)
        finish()
    }

    private fun setupOnClickListeners(){
        binding.skipTimer.setOnClickListener {
            turnAlarmOff()
            unBindAndCloseTimerService()
            goToNextExercise()
        }
        binding.goBack.setOnClickListener {
            turnAlarmOff()
            onBackPressed()
        }
        binding.settings.setOnClickListener {
            goToTimerSettingsActivity()
            finish()
        }
    }

    private fun turnAlarmOff(){
        if (vibrator.hasVibrator()) {
            vibrator.cancel()
        }
    }

    private fun unBindAndCloseTimerService(){
        if (isTimerServiceBound){
            timerService?.timeLeft?.removeObservers(this)
            timerService?.isAlarmOff?.removeObservers(this)
            unbindService(timerServiceConnection)
            isTimerServiceBound = false
        }
        val intent = Intent(this, TimerService::class.java)
        stopService(intent)
    }

    private fun goToNextExercise(){
        isGoingToNextExercise = true
        viewModel.goToNextExercise()
    }

    private fun goToTimerSettingsActivity(){
        val intent = Intent(this, SettingsTimerActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        if (isConfigurationChange.not() && isGoingToNextExercise.not()){
            timerService?.goToForegroundService()
        }
    }

    override fun onDestroy() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
        if (isConfigurationChange.not()){
            unBindAndCloseTimerService()
        }
        turnAlarmOff()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        isConfigurationChange = true
    }

}