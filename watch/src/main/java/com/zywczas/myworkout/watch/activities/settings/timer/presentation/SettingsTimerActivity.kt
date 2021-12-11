package com.zywczas.myworkout.watch.activities.settings.timer.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.databinding.ActivitySettingsTimerBinding

class SettingsTimerActivity : BaseActivity() {

    private var binding: ActivitySettingsTimerBinding by autoRelease()
    private val viewModel: SettingsTimerViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun setupLiveDataObservers(){
        viewModel.breakPeriodInSeconds.observe(this) { binding.time.text = getString(R.string.number_of_seconds, it) }
    }

    private fun setupOnClickListeners(){
        binding.change.setOnClickListener {

        }
    }



}