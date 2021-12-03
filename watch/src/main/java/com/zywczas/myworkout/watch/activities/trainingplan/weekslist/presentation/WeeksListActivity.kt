package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.Week
import com.zywczas.myworkout.watch.adapters.DiffUtilCallback
import com.zywczas.myworkout.watch.adapters.SettingsItem
import com.zywczas.myworkout.watch.adapters.WeekItem
import com.zywczas.myworkout.watch.databinding.ActivityWeeksListBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class WeeksListActivity : BaseActivity() {

    private val viewModel: WeeksListViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivityWeeksListBinding
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeeksListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.weeksList.setup()
        viewModel.getPlannedWeeks()
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun WearableRecyclerView.setup(){
        //todo sprobowac powrzucac do layoutu
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@WeeksListActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers(){
        viewModel.weeks.observe(this){ weeks -> FastAdapterDiffUtil.set(itemAdapter, weeks.toGenericItems(), DiffUtilCallback()) }
        viewModel.isEmptyPlanMessageGone.observe(this){ binding.emptyPlanMessage.isGone = it }
    }

    private fun List<Week>.toGenericItems(): List<GenericItem> {
        val genericItems = mutableListOf<GenericItem>()
        forEach { genericItems.add(WeekItem(it){ id -> goToWeekActivity(id) }) }
        genericItems.add(SettingsItem{ goToSettingsActivity() })
        return genericItems
    }

    private fun goToWeekActivity(weekId: Long){
        //todo przejscie
    }

    private fun goToSettingsActivity(){
        //todo przejscie
    }

    private fun setupOnClickListeners(){
        binding.emptyPlanMessage.setOnClickListener { goToSettingsActivity() }
    }

}