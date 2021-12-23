package com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.day.presentation.DayActivity
import com.zywczas.myworkout.watch.activities.trainingplan.week.presentation.WeekActivity
import com.zywczas.myworkout.watch.databinding.ActivityDayBinding
import com.zywczas.myworkout.watch.databinding.ActivityExerciseBinding

class ExerciseActivity : BaseActivity() {

    private var binding: ActivityExerciseBinding by autoRelease()
    private val viewModel: ExerciseViewModel by viewModels { viewModelFactory }
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }
    private val exerciseId by lazy { intent?.getLongExtra(DayActivity.KEY_EXERCISE_ID, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}