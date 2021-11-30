package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.zywczas.myworkout.watch.databinding.ActivityWeeksListBinding

class WeeksListActivity : ComponentActivity() {

    private lateinit var binding: ActivityWeeksListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeeksListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}