package com.zywczas.myworkout.watch.activities.trainingplan.addexercise.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.zywczas.common.extetions.showToast
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.day.presentation.DayActivity
import com.zywczas.myworkout.watch.databinding.ActivityAddExerciseBinding

class AddExerciseActivity : BaseActivity() {

    private var binding: ActivityAddExerciseBinding by autoRelease()
    private val viewModel: AddExerciseViewModel by viewModels { viewModelFactory }
    private val dayId by lazy { intent?.getLongExtra(DayActivity.KEY_DAY_ID, 0) ?: 0L }
    private val exerciseName by lazy { intent?.getStringExtra(DayActivity.KEY_EXERCISE_NAME) ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun setupLiveDataObservers(){
        viewModel.message.observe(this){ showToast(it) }
        viewModel.isExerciseAdded.observe(this){ if (it) {
            showToast(R.string.exercise_added)
            finish()
        }}
    }

    private fun setupOnClickListeners(){
        binding.save.setOnClickListener { addExercise() }
    }

    private fun addExercise(){
        viewModel.addExercise(
            dayId = dayId,
            name = exerciseName,
            sets = binding.sets.text.toString(),
            reps = binding.reps.text.toString(),
            weight = binding.weight.text.toString()
        )
    }

}