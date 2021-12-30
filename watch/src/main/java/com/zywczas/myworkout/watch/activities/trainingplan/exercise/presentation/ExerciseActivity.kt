package com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.changeweight.presentation.ChangeWeightActivity
import com.zywczas.myworkout.watch.activities.trainingplan.day.presentation.DayActivity
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain.Exercise
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain.NextExercise
import com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation.TimerActivity
import com.zywczas.myworkout.watch.activities.trainingplan.week.presentation.WeekActivity
import com.zywczas.myworkout.watch.databinding.ActivityExerciseBinding

class ExerciseActivity : BaseActivity() {

    companion object {
        const val KEY_EXERCISE_SET = "KEY_EXERCISE_SET"
        const val KEY_EXERCISE_ID = "KEY_EXERCISE_ID"
    }

    private var binding: ActivityExerciseBinding by autoRelease()
    private val viewModel: ExerciseViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun setupLiveDataObservers(){
        viewModel.exercise.observe(this) { showExercise(it) }
        viewModel.isTimerButtonVisible.observe(this){ binding.timer.isVisible = it }
        viewModel.isFinishExerciseButtonVisible.observe(this){ binding.finishExercises.isVisible = it }
        viewModel.nextExercise.observe(this){ goToTimerActivity(it) }
        viewModel.isProgressBarVisible.observe(this){ binding.progressBar.isVisible = it }
        viewModel.goToDayId.observe(this){ goToDayActivity(it) }
    }

    private fun showExercise(exercise: Exercise){
        binding.name.text = exercise.name
        binding.sets.text = exercise.setsQuantity.toString()
        binding.reps.text = exercise.repsQuantity
        binding.weight.text = exercise.weight.toString()
        binding.currentSet.text = exercise.currentSet.toString()
    }

    private fun goToTimerActivity(nextExercise: NextExercise){
        val intent = Intent(this, TimerActivity::class.java).apply {
            putExtra(DayActivity.KEY_EXERCISE_ID, nextExercise.id)
            putExtra(KEY_EXERCISE_SET, nextExercise.set)
        }
        startActivity(intent)
    }

    private fun goToDayActivity(dayId: Long){
        val intent = Intent(this, DayActivity::class.java).apply {
            putExtra(WeekActivity.KEY_DAY_ID, dayId)
        }
        startActivity(intent)
    }

    private fun setupOnClickListeners(){
        binding.timer.setOnClickListener { startTimerToNextExercise() }
        binding.finishExercises.setOnClickListener { finishExercises() }
        binding.settings.setOnClickListener { goToChangeWeightActivity() }
    }

    private fun startTimerToNextExercise(){
        viewModel.startTimerToNextExercise()
    }

    private fun finishExercises(){
        viewModel.finishExercises()
    }

    private fun goToChangeWeightActivity(){
        val intent = Intent(this, ChangeWeightActivity::class.java).apply {
            putExtra(KEY_EXERCISE_ID, getExerciseIdFromUpdatedIntent())
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getExerciseDetails(getExerciseIdFromUpdatedIntent())
    }

    private fun getExerciseIdFromUpdatedIntent(): Long = intent.getLongExtra(DayActivity.KEY_EXERCISE_ID, 0)

}