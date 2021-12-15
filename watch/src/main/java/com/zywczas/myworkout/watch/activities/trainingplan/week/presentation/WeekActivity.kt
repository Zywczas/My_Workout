package com.zywczas.myworkout.watch.activities.trainingplan.week.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.DaysElements
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation.WeeksListActivity
import com.zywczas.myworkout.watch.adapters.DiffUtilCallback
import com.zywczas.myworkout.watch.adapters.SettingsItem
import com.zywczas.myworkout.watch.adapters.TitleItem
import com.zywczas.myworkout.watch.databinding.ActivityWeekBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class WeekActivity : BaseActivity() {

    private var binding: ActivityWeekBinding by autoRelease()
    private val viewModel: WeekViewModel by viewModels{ viewModelFactory }
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }
    private val weekId by lazy { intent?.getLongExtra(WeeksListActivity.KEY_WEEK_ID, 0) ?: 0L }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeekBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.daysList.setup()
        viewModel.getDaysList(weekId)
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun WearableRecyclerView.setup(){
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@WeekActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers(){
        viewModel.isEmptyPlanMessageGone.observe(this){ binding.emptyPlanMessage.isGone = it }
        viewModel.daysElements.observe(this){ days -> FastAdapterDiffUtil.set(itemAdapter, days.toAdapterItems(), DiffUtilCallback()) }
    }

    private fun List<DaysElements>.toAdapterItems(): List<GenericItem> = map {
        when(it){
            is DaysElements.WeekHeader -> TitleItem(getString(it.title))
            is DaysElements.Day -> DaI(it){ id -> goToDayActivity(id) }
            is DaysElements.AddNewDay -> SettingsItem(getString(it.title)){ addNewDay() }
            is DaysElements.CopyWeek -> SettingsItem(getString(it.title)){ copyWeek() }
        }
    }

    private fun goToDayActivity(dayId: Long){

    }

    private fun addNewDay(){

    }

    private fun copyWeek(){

    }

    private fun setupOnClickListeners(){

    }

}