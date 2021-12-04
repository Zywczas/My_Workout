package com.zywczas.myworkout.watch.activities.settings.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.zywczas.myworkout.watch.databinding.ActivitySettingsMainBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class SettingsMainActivity : ComponentActivity() {

    private lateinit var binding: ActivitySettingsMainBinding
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsList.setup()
    }

    private fun WearableRecyclerView.setup(){
        //todo sprobowac powrzucac do layoutu
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@SettingsMainActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    //todo dac empty space item i zobaczyc jak to bedzie wygladac

}