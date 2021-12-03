package com.zywczas.myworkout.watch.activities.settings.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.zywczas.myworkout.watch.databinding.ActivitySettingsMainBinding

class SettingsMainActivity : ComponentActivity() {

    private lateinit var binding: ActivitySettingsMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}