package com.zywczas.myworkout.watch.activities.trainingplan.week.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
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
import com.zywczas.myworkout.watch.activities.trainingplan.day.presentation.DayActivity
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.WeekElements
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation.WeeksListActivity
import com.zywczas.myworkout.watch.activityresultcontracts.registerVoiceRecognition
import com.zywczas.myworkout.watch.adapters.*
import com.zywczas.myworkout.watch.databinding.ActivityWeekBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeekActivity : ComponentActivity() {

    companion object {
        const val KEY_DAY_ID = "KEY_DAY_ID"
    }

    private var binding: ActivityWeekBinding by autoRelease()
    private val viewModel: WeekViewModel by viewModels()
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }
    private val weekId by lazy { intent.getLongExtra(WeeksListActivity.KEY_WEEK_ID, 0) }
    private val voiceRecognitionLauncher = registerVoiceRecognition { dayName -> viewModel.addNewDay(dayName, weekId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeekBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.daysList.setup()
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun WearableRecyclerView.setup(){
        addVerticalItemDivider(R.drawable.divider_transparent_vertical)
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@WeekActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers(){
        viewModel.message.observe(this){ showToast(it) }
        viewModel.isProgressBarVisible.observe(this){ binding.progressBar.isVisible = it }
        viewModel.isEmptyPlanMessageVisible.observe(this){ binding.emptyPlanMessage.isVisible = it }
        viewModel.weekElements.observe(this){ days -> FastAdapterDiffUtil.set(itemAdapter, days.toAdapterItems(), DiffUtilCallback()) }
        viewModel.closeActivity.observe(this){ if (it) finish() }
    }

    private fun List<WeekElements>.toAdapterItems(): List<GenericItem> = map {
        when(it){
            is WeekElements.WeekHeader -> WeekHeaderItem(it)
            is WeekElements.Day -> WeekOrDayItem(
                weekOrDayId = it.id,
                title = it.name,
                dates = it.displayedDate,
                isFinished = it.isFinished
            ){ id -> goToDayActivity(id) }
            is WeekElements.AddNewDay -> SettingsItem(getString(it.title)){ addNewDay() }
            is WeekElements.CopyWeek -> SettingsItem(getString(it.title)){ copyWeek() }
            is WeekElements.DeleteWeek -> DeleteItem(getString(it.title)){ deleteWeek() }
        }
    }

    private fun goToDayActivity(dayId: Long){
        val intent = Intent(this, DayActivity::class.java).apply {
            putExtra(KEY_DAY_ID, dayId)
        }
        startActivity(intent)
    }

    private fun addNewDay(){
        voiceRecognitionLauncher.launch()
    }

    private fun copyWeek(){
        viewModel.copyWeek(weekId)
    }

    private fun deleteWeek(){
        viewModel.deleteWeek(weekId)
    }

    private fun setupOnClickListeners(){
        binding.emptyPlanMessage.setOnClickListener { voiceRecognitionLauncher.launch() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDaysList(weekId)
    }

}