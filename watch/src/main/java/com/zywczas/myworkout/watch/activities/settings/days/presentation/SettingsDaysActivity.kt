package com.zywczas.myworkout.watch.activities.settings.days.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.settings.weeks.presentation.SettingsWeeksActivity
import com.zywczas.myworkout.watch.databinding.ActivitySettingsDaysBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class SettingsDaysActivity : BaseActivity() {

    private var binding: ActivitySettingsDaysBinding by autoRelease()
    private val viewModel: SettingsDaysViewModel by viewModels{ viewModelFactory }
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }
    private val weekId by lazy { intent?.getLongExtra(SettingsWeeksActivity.KEY_WEEK_ID, 0) ?: 0L }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsDaysBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsList.setup()
        viewModel.getSettingsList(weekId)
        setupLiveDataObservers()
    }

    private fun WearableRecyclerView.setup(){
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@SettingsDaysActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers(){

    }

}