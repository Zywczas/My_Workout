package com.zywczas.myworkout.watch.activities.settings.weeks.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.DiffCallback
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.zywczas.common.extetions.showToast
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.settings.main.domain.SettingsMainElements
import com.zywczas.myworkout.watch.activities.settings.weeks.domain.SettingsWeeksElements
import com.zywczas.myworkout.watch.activityresultcontracts.registerVoiceRecognition
import com.zywczas.myworkout.watch.adapters.DiffUtilCallback
import com.zywczas.myworkout.watch.adapters.SettingsItem
import com.zywczas.myworkout.watch.adapters.SettingsWeekItem
import com.zywczas.myworkout.watch.adapters.TitleItem
import com.zywczas.myworkout.watch.databinding.ActivitySettingsWeeksBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class SettingsWeeksActivity : BaseActivity() {

    private var binding: ActivitySettingsWeeksBinding by autoRelease()
    private val viewModel: SettingsWeeksViewModel by viewModels{ viewModelFactory }
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }

    private val voiceRecognitionLauncher = registerVoiceRecognition { weekName -> viewModel.addNewWeek(weekName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsWeeksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsList.setup()
        setupLiveDataObservers()
    }

    private fun WearableRecyclerView.setup(){
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@SettingsWeeksActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers(){
        viewModel.message.observe(this){ showToast(it) }
        viewModel.settingsElements.observe(this){ settings -> FastAdapterDiffUtil.set(itemAdapter, settings.toAdapterItems(), DiffUtilCallback()) }
    }

    private fun List<SettingsWeeksElements>.toAdapterItems(): List<GenericItem> = map {
        when (it) {
            is SettingsWeeksElements.Title -> TitleItem(getString(it.title))
            is SettingsWeeksElements.Week -> SettingsWeekItem(it){ id -> goToSettingsDaysActivity(id) }
            is SettingsWeeksElements.AddNewWeek -> SettingsItem(getString(it.title)){ addNewWeek() }
        }
    }

    private fun goToSettingsDaysActivity(weekId: Long){
        //todo
    }

    private fun addNewWeek(){
        voiceRecognitionLauncher.launch()
    }

}