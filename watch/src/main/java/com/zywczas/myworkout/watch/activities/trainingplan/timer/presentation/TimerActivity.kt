package com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.settings.timer.presentation.SettingsTimerActivity
import com.zywczas.myworkout.watch.databinding.ActivityTimerBinding
import com.zywczas.myworkout.watch.services.timer.TimerService

class TimerActivity : BaseActivity() {

    private var binding: ActivityTimerBinding by autoRelease()
    private val viewModel: TimerViewModel by viewModels { viewModelFactory } //todo chyba do wylotu
    private var timerService: TimerService? = null
    private var isTimerServiceBound = false
    private var isConfigurationChange = false
    private val vibrator by lazy { getSystemService(Vibrator::class.java) }
    private var isGoingToNextExercise = false

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
                turnAlarmOn()
                showFinishedCounter()
            }
        }
    }

    private fun turnAlarmOn(){
        if (vibrator.hasVibrator()) {
//        vibrator.vibrate(3000L)//todo deprecated
            vibrator.vibrate(VibrationEffect.createOneShot(3000L, VibrationEffect.DEFAULT_AMPLITUDE)) //todo jest jeszcze sposob by
//        binding.root.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS) //todo inny sposob na wibracje, i niby nie trzeba permission
        }
    }

    private fun showFinishedCounter(){
        binding.counterHeader.isVisible = false
        binding.nextExercise.isVisible = false
        binding.goBack.isVisible = false
        binding.settings.isVisible = false
        binding.skipTimer.setText(R.string.nextExercise)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isConfigurationChange = false
        bindTimerService()
        setupOnClickListeners()
    }

    private fun bindTimerService(){ //todo dac jakies sprawdzenie gdzies czy ustawiony czas to nie jest zero albo 1, jezeli bedzie to jakos przeskakiwac timer service, moze dac sprawdzenie we wczesniejszej aktywnosci
        val serviceIntent = Intent(this, TimerService::class.java)
        bindService(serviceIntent, timerServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun setupOnClickListeners(){
        binding.skipTimer.setOnClickListener {
            turnAlarmOff()
            unBindAndCloseTimerService()
            goToNextExercise()
            isGoingToNextExercise = true
            finish()
        }
        binding.goBack.setOnClickListener { onBackPressed() }
        binding.settings.setOnClickListener {
            unBindAndCloseTimerService()
            goToTimerSettingsActivity()
        }
    }

    private fun turnAlarmOff(){
        //todo wylaczyc wibracje jesli sa wlaczone, sprawdzic czy to ma sens
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(VibrationEffect.createOneShot(100L, VibrationEffect.DEFAULT_AMPLITUDE)) //todo sprawdzic czy ma to sens
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
        //todo zapisanie jakie jest kolejne cwiczenie jesli jeszcze nie zapisalem
        //przejscie do Exercise Activity i wczytanie odpowiedniego cwiczenia i serii todo
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
        if (isConfigurationChange.not()){
            unBindAndCloseTimerService()
        }
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        isConfigurationChange = true
    }

}