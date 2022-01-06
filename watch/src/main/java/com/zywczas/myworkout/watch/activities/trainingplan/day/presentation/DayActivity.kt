package com.zywczas.myworkout.watch.activities.trainingplan.day.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.zywczas.common.extetions.addVerticalItemDivider
import com.zywczas.common.extetions.showToast
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.addexercise.presentation.AddExerciseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.day.domain.DayElements
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation.ExerciseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.week.presentation.WeekActivity
import com.zywczas.myworkout.watch.activityresultcontracts.registerVoiceRecognition
import com.zywczas.myworkout.watch.adapters.*
import com.zywczas.myworkout.watch.databinding.ActivityDayBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class DayActivity : BaseActivity() {

    companion object {
        const val KEY_DAY_ID = "KEY_DAY_ID"
        const val KEY_EXERCISE_NAME = "KEY_EXERCISE_NAME"
        const val KEY_EXERCISE_ID = "KEY_EXERCISE_ID"
    }

    private var binding: ActivityDayBinding by autoRelease()
    private val viewModel: DayViewModel by viewModels { viewModelFactory }
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }
    private var dayId = 0L
    private val voiceRecognitionLauncher = registerVoiceRecognition { exerciseName -> viewModel.addNewExercise(exerciseName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dayId = intent.getLongExtra(WeekActivity.KEY_DAY_ID, 0)
        binding.exerciseList.setup()
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun WearableRecyclerView.setup() {
        addVerticalItemDivider(R.drawable.divider_transparent_vertical)
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@DayActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers() {
        viewModel.message.observe(this) { showToast(it) }
        viewModel.isProgressBarVisible.observe(this) { binding.progressBar.isVisible = it }
        viewModel.isEmptyPlanMessageVisible.observe(this) { binding.emptyPlanMessage.isVisible = it }
        viewModel.dayElements.observe(this) { days -> FastAdapterDiffUtil.set(itemAdapter, days.toAdapterItems(), DiffUtilCallback()) }
        viewModel.newExerciseName.observe(this) { goToAddExerciseActivity(it) }
        viewModel.nextExerciseId.observe(this){ goToExerciseActivity(it) }
        viewModel.closeActivity.observe(this){ if (it) finish() }
    }

    private fun List<DayElements>.toAdapterItems(): List<GenericItem> = map {
        when (it) {
            is DayElements.DayHeader -> TitleItem(it.displayedDate)
            is DayElements.GoToExercise -> GoToExerciseItem(getString(it.title)) { startExercises() }
            is DayElements.Exercise -> ExerciseItem(it)
            is DayElements.Cardio -> CardioItem()
            is DayElements.AddNewExercise -> SettingsItem(getString(it.title)) { addNewExercise() }
            is DayElements.AddCardio -> SettingsItem(getString(it.title)) { addCardio() }
            is DayElements.CopyDay -> SettingsItem(getString(it.title)) { copyDay() }
            is DayElements.DeleteDay -> DeleteItem(getString(it.title)) { deleteDay() }
        }
    }

    private fun startExercises(){
        viewModel.startExercises()
    }

    private fun addNewExercise() {
        voiceRecognitionLauncher.launch()
    }

    private fun addCardio(){
        viewModel.addCardio(dayId)
    }

    private fun copyDay() {
        viewModel.copyDay(dayId)
    }

    private fun deleteDay(){
        viewModel.deleteDay(dayId)
    }

    private fun goToAddExerciseActivity(exerciseName: String){
        val intent = Intent(this, AddExerciseActivity::class.java).apply {
            putExtra(KEY_DAY_ID, dayId)
            putExtra(KEY_EXERCISE_NAME, exerciseName)
        }
        startActivity(intent)
    }

    private fun goToExerciseActivity(exerciseId: Long){
        val intent = Intent(this, ExerciseActivity::class.java).apply {
            putExtra(KEY_EXERCISE_ID, exerciseId)
        }
        startActivity(intent)
    }

    private fun setupOnClickListeners() {
        binding.emptyPlanMessage.setOnClickListener { voiceRecognitionLauncher.launch() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getExerciseList(dayId)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        dayId = intent?.getLongExtra(WeekActivity.KEY_DAY_ID, 0) ?: 0L
    }

}