package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import android.content.Intent
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
import com.zywczas.myworkout.watch.activities.settings.main.presentation.SettingsMainActivity
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksList
import com.zywczas.myworkout.watch.adapters.DiffUtilCallback
import com.zywczas.myworkout.watch.adapters.SettingsItem
import com.zywczas.myworkout.watch.adapters.TitleItem
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
        viewModel.weeksList.observe(this){ weeks -> FastAdapterDiffUtil.set(itemAdapter, weeks.toAdapterItems(), DiffUtilCallback()) }
        viewModel.isEmptyPlanMessageGone.observe(this){ binding.emptyPlanMessage.isGone = it }
    }

    private fun List<WeeksList>.toAdapterItems(): List<GenericItem> = map {
        when(it){
            is WeeksList.Title -> TitleItem(getString(it.title))
            is WeeksList.Week -> WeekItem(it){ id -> goToWeekActivity(id) }
            is WeeksList.Settings -> SettingsItem{ goToSettingsActivity() }
        }
    }

    private fun goToWeekActivity(weekId: Long){
        //todo przejscie
    }

    private fun goToSettingsActivity(){
        val intent = Intent(this, SettingsMainActivity::class.java)
        startActivity(intent)
    }

    private fun setupOnClickListeners(){
        binding.emptyPlanMessage.setOnClickListener { goToSettingsActivity() }
    }

}