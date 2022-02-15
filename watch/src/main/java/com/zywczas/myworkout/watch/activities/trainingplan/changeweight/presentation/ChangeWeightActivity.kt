package com.zywczas.myworkout.watch.activities.trainingplan.changeweight.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation.ExerciseActivity
import com.zywczas.myworkout.watch.databinding.ActivityChangeWeightBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeWeightActivity : ComponentActivity() {

    private var binding: ActivityChangeWeightBinding by autoRelease()
    private val viewModel: ChangeWeightViewModel by viewModels()
    private val exerciseId by lazy { intent.getLongExtra(ExerciseActivity.KEY_EXERCISE_ID, 0L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getCurrentExercise(exerciseId)
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun setupLiveDataObservers(){
        viewModel.exercise.observe(this){ binding.weight.setText(it.weight.toString()) }
        viewModel.finishActivity.observe(this){ if (it) { finish() } }
    }

    private fun setupOnClickListeners(){
        binding.save.setOnClickListener { viewModel.saveWeight(binding.weight.text.toString()) }
    }

}