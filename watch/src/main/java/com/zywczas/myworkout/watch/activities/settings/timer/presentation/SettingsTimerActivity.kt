package com.zywczas.myworkout.watch.activities.settings.timer.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation.TimerActivity
import com.zywczas.myworkout.watch.databinding.ActivitySettingsTimerBinding

class SettingsTimerActivity : BaseActivity() {

    private var binding: ActivitySettingsTimerBinding by autoRelease()
    private val viewModel: SettingsTimerViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners(){
        binding.time.setOnClickListener {
            goToTimerActivity() //todo to pozniej poprawic
        }
    }

    private fun goToTimerActivity(){
        val intent = Intent(this, TimerActivity::class.java)
        startActivity(intent)
    }

}