package com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import androidx.activity.viewModels
import com.zywczas.common.extetions.logD
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.settings.timer.presentation.SettingsTimerActivity
import com.zywczas.myworkout.watch.databinding.ActivityTimerBinding
import com.zywczas.myworkout.watch.services.timer.TimerService

class TimerActivity : BaseActivity() {

    init {
        logD("init aktywnosci") //todo uruchamia sie kilka razy bo serwis tworzy nowe przy kliknieciu w ikonke, do poprawy, moze da sie jakos z back stacku otwierac ta sama
    }

    private var binding: ActivityTimerBinding by autoRelease()
    private val viewModel: TimerViewModel by viewModels { viewModelFactory }
    private var timerService: TimerService? = null
    private var isTimerServiceBound = false
    private var isConfigurationChange = false

    private val timerServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as? TimerService.LocalBinder
            timerService = binder?.timerService
            isTimerServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            timerService = null
            isTimerServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isConfigurationChange = false
        bindTimerService()
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun bindTimerService(){ //todo dac jakies sprawdzenie gdzies czy ustawiony czas to nie jest zero albo 1, jezeli bedzie to jakos przeskakiwac timer service, moze dac sprawdzenie we wczesniejszej aktywnosci
        val serviceIntent = Intent(this, TimerService::class.java)
        bindService(serviceIntent, timerServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun setupLiveDataObservers(){

    }

    private fun setupOnClickListeners(){
        binding.skipTimer.setOnClickListener {
            unBindAndCloseTimerService()
            goToNextExercise()
            finish()
        }
        binding.goBack.setOnClickListener { onBackPressed() } //todo nie jestem pewien czy to jest ok
        binding.settings.setOnClickListener {
            unBindAndCloseTimerService()
            goToTimerSettingsActivity()
        }
    }

    private fun unBindAndCloseTimerService(){
        if (isTimerServiceBound){
            unbindService(timerServiceConnection)
            isTimerServiceBound = false
        }
        val intent = Intent(this, TimerService::class.java)
        stopService(intent)
    }

    private fun goToNextExercise(){
        //przejscie do Exercise Activity i wczytanie odpowiedniego cwiczenia i serii todo
    }



    private fun goToTimerSettingsActivity(){
        val intent = Intent(this, SettingsTimerActivity::class.java)
        startActivity(intent)
    }
//todo
    //3. jak serwis skonczy odliczanie to wlaczyc wibracje i obudzic aktywnosc timer, a po wejsciu w aktywnosc wylaczyc serwis

    override fun onStop() {
        super.onStop()
        if (isConfigurationChange.not()){
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