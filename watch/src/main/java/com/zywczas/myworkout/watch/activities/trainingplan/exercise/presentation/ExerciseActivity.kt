package com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.day.presentation.DayActivity
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain.Exercise
import com.zywczas.myworkout.watch.databinding.ActivityExerciseBinding

class ExerciseActivity : BaseActivity() {

    private var binding: ActivityExerciseBinding by autoRelease()
    private val viewModel: ExerciseViewModel by viewModels { viewModelFactory }
    private val exerciseId by lazy { intent?.getLongExtra(DayActivity.KEY_EXERCISE_ID, 0) ?: 0L }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun setupLiveDataObservers(){
        viewModel.exercise.observe(this) { showExercise(it) }
    }

    private fun showExercise(exercise: Exercise){
        binding.name.text = exercise.name
        binding.sets.text = exercise.setsQuantity.toString()
        binding.reps.text = exercise.repsQuantity
        binding.weight.text = exercise.weight.toString()
        binding.currentSet.text = exercise.currentSet.toString()
    }

    private fun setupOnClickListeners(){
        binding.timer.setOnClickListener { goToTimerActivity() }
        binding.setting.setOnClickListener { goToChangeWeightActivity() }
    }

    private fun goToTimerActivity(){
        //todo tutaj dac od razu podbicie serii - w view modelu
    }

    private fun goToChangeWeightActivity(){
        //todo
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCurrentExercise(exerciseId)
    }

}