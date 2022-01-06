package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

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
import com.zywczas.common.extetions.showToast
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.week.presentation.WeekActivity
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListElements
import com.zywczas.myworkout.watch.activityresultcontracts.registerVoiceRecognition
import com.zywczas.myworkout.watch.adapters.*
import com.zywczas.myworkout.watch.databinding.ActivityWeeksListBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class WeeksListActivity : BaseActivity() {

    companion object {
        const val KEY_WEEK_ID = "KEY_WEEK_ID"
    }

    private val viewModel: WeeksListViewModel by viewModels { viewModelFactory }
    private var binding: ActivityWeeksListBinding by autoRelease()
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }

    private val voiceRecognitionLauncher = registerVoiceRecognition { weekName -> viewModel.addNewWeek(weekName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeeksListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.weeksList.setup()
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun WearableRecyclerView.setup(){
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@WeeksListActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers(){
        viewModel.message.observe(this){ showToast(it) }
        viewModel.weeksListElements.observe(this){ weeks -> FastAdapterDiffUtil.set(itemAdapter, weeks.toAdapterItems(), DiffUtilCallback()) }
        viewModel.isEmptyPlanMessageVisible.observe(this){ binding.emptyPlanMessage.isVisible = it }
    }

    private fun List<WeeksListElements>.toAdapterItems(): List<GenericItem> = map {
        when(it){
            is WeeksListElements.Title -> TitleItem(getString(it.title))
            is WeeksListElements.Week -> WeekOrDayItem(
                weekOrDayId = it.id,
                title = it.name,
                dates = it.displayedDates,
                isFinished = it.isFinished
            ){ id -> goToWeekActivity(id) }
            is WeeksListElements.AddNewWeek -> SettingsItem(getString(it.title)){ addNewWeek() }
        }
    }

    private fun goToWeekActivity(weekId: Long){
        val intent = Intent(this, WeekActivity::class.java).apply {
            putExtra(KEY_WEEK_ID, weekId)
        }
        startActivity(intent)
    }

    private fun addNewWeek(){
        voiceRecognitionLauncher.launch()
    }

    private fun setupOnClickListeners(){
        binding.emptyPlanMessage.setOnClickListener { voiceRecognitionLauncher.launch() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getWeeksList()
    }

}