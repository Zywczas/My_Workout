package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.Week
import com.zywczas.myworkout.watch.adapters.DiffUtilCallback
import com.zywczas.myworkout.watch.adapters.WeekItem
import com.zywczas.myworkout.watch.databinding.ActivityWeeksListBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class WeeksListActivity : BaseActivity() {

    private val viewModel: WeeksListViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivityWeeksListBinding
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }
    private val recyclerAdapter by lazy { FastAdapter.with(itemAdapter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeeksListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.weeksList.setup()
        viewModel.getPlannedWeeks()
        setupLiveDataObservers()
    }

    private fun WearableRecyclerView.setup(){
        //todo sprobowac powrzucac do layoutu
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@WeeksListActivity, CustomScrollingLayoutCallback())
        adapter = recyclerAdapter
    }

    private fun setupLiveDataObservers(){
        viewModel.weeks.observe(this){ weeks -> FastAdapterDiffUtil.set(itemAdapter, weeks.toWeekItems(), DiffUtilCallback()) }
        viewModel.isEmptyPlanMessageGone.observe(this){ binding.emptyPlanMessage.isGone = it }
    }

    private fun List<Week>.toWeekItems(): List<WeekItem> = map { WeekItem(it) }

}