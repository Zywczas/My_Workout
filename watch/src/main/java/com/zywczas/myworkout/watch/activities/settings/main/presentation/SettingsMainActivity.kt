package com.zywczas.myworkout.watch.activities.settings.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.settings.main.domain.SettingsMainElements
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksElements
import com.zywczas.myworkout.watch.adapters.DiffUtilCallback
import com.zywczas.myworkout.watch.adapters.SettingsItem
import com.zywczas.myworkout.watch.adapters.TitleItem
import com.zywczas.myworkout.watch.databinding.ActivitySettingsMainBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class SettingsMainActivity : BaseActivity() {

    private lateinit var binding: ActivitySettingsMainBinding
    private val viewModel: SettingsMainViewModel by viewModels{ viewModelFactory }
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsList.setup()
        viewModel.getSettingsList()
        setupObservers()
    }

    private fun WearableRecyclerView.setup(){
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@SettingsMainActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupObservers(){
        viewModel.settingsElements.observe(this){ settings -> FastAdapterDiffUtil.set(itemAdapter, settings.toAdapterItems(), DiffUtilCallback()) }
    }

    private fun List<SettingsMainElements>.toAdapterItems(): List<GenericItem> = map {
        when (it) {
            is SettingsMainElements.Title -> TitleItem(getString(it.title))
            is SettingsMainElements.Trainings -> SettingsItem(getString(it.title)){ goToSettingsWeeksActivity() }
            is SettingsMainElements.BreakInterval -> SettingsItem(getString(it.title)){ goToSettingsTimerActivity() }
        }
    }

    private fun goToSettingsWeeksActivity(){
        //todo
    }

    private fun goToSettingsTimerActivity(){
        //todo
    }

}