package com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
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

    private var binding: ActivityTimerBinding by autoRelease()
    private val viewModel: TimerViewModel by viewModels { viewModelFactory }
    private var timerService: TimerService? = null
    private var isTimerServiceBound = false
    private var isConfigurationChange = false
    private val vibrator by lazy { getSystemService(Vibrator::class.java) }
    private var isGoingToNextExercise = false
    private val exerciseId by lazy { intent.getLongExtra(DayActivity.KEY_EXERCISE_ID, 0L) }
    private val nextExerciseSet by lazy { intent.getIntExtra(ExerciseActivity.KEY_EXERCISE_SET, 0) }
    private var shouldKeepScreenOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logD("onCreate")
        isConfigurationChange = false //todo sprawdzic czy to jest potrzebne
        bindTimerService()
        viewModel.getExerciseDetails(exerciseId, nextExerciseSet)
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    //todo dac pozniej wylaczanie aktywnosci po 10 sek

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
                    logD("wlacza alarm z live daty")
                    turnAlarmOn()
                    showFinishedCounter()
                }
            }
        }
    }

    private fun turnAlarmOn(){
        if (vibrator.hasVibrator()) {
            logD("wlacz alarm")
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

    override fun onStart() {
        super.onStart()
        logD("onStart")

    }

    override fun onResume() {
        super.onResume()
        logD("onResume")
    }

    override fun onStop() {
        super.onStop()
        if (isConfigurationChange.not() && isGoingToNextExercise.not()){
            timerService?.goToForegroundService()
        }
    }

    override fun onDestroy() {
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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logD("onNewIntent")
        val hasTimerServiceFinishedCounting = intent?.getBooleanExtra(TimerService.KEY_HAS_TIMER_SERVICE_FINISHED_COUNTING, false) ?: false
        if (hasTimerServiceFinishedCounting) {
            logD("FLAG_KEEP_SCREEN_ON true")
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            logD("FLAG_KEEP_SCREEN_ON false")
        }
    }

}