package com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.viewModels
import com.zywczas.common.extetions.logD
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.databinding.ActivityTimerBinding
import com.zywczas.myworkout.watch.services.timer.TimerService

class TimerActivity : BaseActivity() {

    private var binding: ActivityTimerBinding by autoRelease()
    private val viewModel: TimerViewModel by viewModels { viewModelFactory }
    private var timerService: TimerService? = null
    private var isTimerServiceBound = false

    private val timerServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as? TimerService.LocalBinder
            timerService = binder?.timerService
            isTimerServiceBound = true
            logD(TimerActivity::class, "onServiceConnected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            timerService = null
            logD("ustawiam isTimerServiceBound = false w onServiceDisconnected")
            isTimerServiceBound = false
            logD(TimerActivity::class, "onServiceDisconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logD("aktywnosc wystartowala")
        bindTimerService()
        setupLiveDataObservers()
    }

    private fun bindTimerService(){
        logD("wiaze service")
        val serviceIntent = Intent(this, TimerService::class.java)
        bindService(serviceIntent, timerServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun setupLiveDataObservers(){
        timerService?.liveData?.observe(this){
            binding.time.text = it.toString()
        }
    }


    override fun onStop() {
        super.onStop()
        unbindTimerService()
    }

    private fun unbindTimerService(){
        if (isTimerServiceBound){
            unbindService(timerServiceConnection)
            logD("ustawiam isTimerServiceBound = false w unbindTimerService")
            isTimerServiceBound = false
        }
    }

}

//todo ousuwac logi