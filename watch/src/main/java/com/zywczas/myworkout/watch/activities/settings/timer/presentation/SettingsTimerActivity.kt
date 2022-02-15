package com.zywczas.myworkout.watch.activities.settings.timer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.zywczas.common.extetions.showToast
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ActivitySettingsTimerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsTimerActivity : ComponentActivity() {

    private var binding: ActivitySettingsTimerBinding by autoRelease()
    private val viewModel: SettingsTimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getBreakPeriod()
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun setupLiveDataObservers() {
        viewModel.breakPeriodInSeconds.observe(this) { binding.time.setText(it.toString()) }
        viewModel.isBreakPeriodSaved.observe(this) {
            if (it) {
                showToast(R.string.saved)
                finish()
            }
        }
    }

    private fun setupOnClickListeners() {
        binding.save.setOnClickListener { viewModel.saveBreakPeriod(binding.time.text.toString().toInt()) }
    }

}