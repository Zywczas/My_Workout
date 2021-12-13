package com.zywczas.myworkout.watch.activities.trainingplan.week.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation.WeeksListActivity
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
    }

    private fun WearableRecyclerView.setup(){
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@WeekActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers(){

    }

}