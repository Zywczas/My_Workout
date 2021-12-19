package com.zywczas.myworkout.watch.activities.trainingplan.addexercise.presentation

import android.os.Bundle
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.databinding.ActivityAddExerciseBinding

class AddExerciseActivity : BaseActivity() {

    private var binding: ActivityAddExerciseBinding by autoRelease()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}