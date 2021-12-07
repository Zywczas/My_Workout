package com.zywczas.myworkout.watch.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation.WeeksListActivity
import com.zywczas.myworkout.watch.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private var binding: ActivityMainBinding by autoRelease()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        binding.motto.setOnClickListener {
            val intent = Intent(this, WeeksListActivity::class.java)
            startActivity(intent)
        }
    }

}