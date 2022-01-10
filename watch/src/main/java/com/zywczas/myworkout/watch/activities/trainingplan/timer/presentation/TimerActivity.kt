package com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.settings.timer.presentation.SettingsTimerActivity
import com.zywczas.myworkout.watch.activities.trainingplan.day.presentation.DayActivity
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation.ExerciseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.NextExercise
import com.zywczas.myworkout.watch.databinding.ActivityTimerBinding

class TimerActivity : BaseActivity() {

    private var binding: ActivityTimerBinding by autoRelease()
    private val viewModel: TimerViewModel by viewModels { viewModelFactory }
    private val exerciseId by lazy { intent.getLongExtra(DayActivity.KEY_EXERCISE_ID, 0L) }
    private val nextExerciseSet by lazy { intent.getIntExtra(ExerciseActivity.KEY_EXERCISE_SET, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getExerciseDetails(exerciseId, nextExerciseSet)
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun setupLiveDataObservers() {
        viewModel.breakPeriodDisplayFormat.observe(this) { binding.breakPeriodSetTime.text = it }
        viewModel.isExerciseLongDescriptionVisible.observe(this) { binding.exerciseLongDescriptionContainer.isVisible = it }
        viewModel.nextExercise.observe(this) { showExercise(it) }
        viewModel.nextExerciseId.observe(this) { goToExerciseActivityAndFinishThisActivity(it) }
        viewModel.breakPeriodInSeconds.observe(this) { startTimer(it) }
    }

    private fun showExercise(exercise: NextExercise) {
        binding.exerciseName.text = exercise.name
        binding.sets.text = exercise.setsQuantity.toString()
        binding.reps.text = exercise.repsQuantity
        binding.weight.text = exercise.weight.toString()
        binding.nextSet.text = exercise.nextSet.toString()
    }

    private fun goToExerciseActivityAndFinishThisActivity(exerciseId: Long) {
        val intent = Intent(this, ExerciseActivity::class.java).apply {
            putExtra(DayActivity.KEY_EXERCISE_ID, exerciseId)
        }
        startActivity(intent)
        finish()
    }

    private fun startTimer(seconds: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_LENGTH, seconds)
        }
        if (intent.resolveActivity(packageManager) != null) { //todo pozniej dac to queries dla resolve Activity
            startActivity(intent)
        }
    }

    private fun setupOnClickListeners() {
        binding.timer.setOnClickListener {
            startBreak()
        }
        binding.skipTimer.setOnClickListener {
            goToNextExercise()
        }
        binding.goBack.setOnClickListener {
            onBackPressed()
        }
        binding.settings.setOnClickListener {
            goToTimerSettingsActivity()
        }
    }

    private fun startBreak() {
        viewModel.startBreak()
    }

    private fun goToNextExercise() {
        viewModel.goToNextExercise()
    }

    private fun goToTimerSettingsActivity() {
        val intent = Intent(this, SettingsTimerActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBreakPeriod()
    }

}