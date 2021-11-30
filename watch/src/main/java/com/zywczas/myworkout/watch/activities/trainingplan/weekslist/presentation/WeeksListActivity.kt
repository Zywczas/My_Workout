package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ActivityMainBinding
import com.zywczas.myworkout.watch.databinding.ActivityWeeksListBinding
import dagger.android.AndroidInjection

class WeeksListActivity : ComponentActivity() {

    private lateinit var binding: ActivityWeeksListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeeksListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}