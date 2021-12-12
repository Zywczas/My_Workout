package com.zywczas.myworkout.watch.activities.settings.weeks.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.databinding.ActivitySettingsWeeksBinding

class SettingsWeeksActivity : BaseActivity() {

    private var binding: ActivitySettingsWeeksBinding by autoRelease()
    private val viewModel: SettingsWeeksViewModel by viewModels{ viewModelFactory }
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsWeeksBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}