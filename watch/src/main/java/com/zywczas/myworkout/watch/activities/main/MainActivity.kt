package com.zywczas.myworkout.watch.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation.WeeksListActivity
import com.zywczas.myworkout.watch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var binding: ActivityMainBinding by autoRelease()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLiveDataObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.goToNextDestination()
    }

    private fun setupLiveDataObservers() {
        viewModel.goToNextActivity.observe(this) { if (it) {
            startActivity(Intent(this, WeeksListActivity::class.java))
            finish()
        }}
    }

}