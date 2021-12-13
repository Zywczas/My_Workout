package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.zywczas.common.extetions.showToast
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.settings.days.presentation.SettingsDaysActivity
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksElements
import com.zywczas.myworkout.watch.activityresultcontracts.registerVoiceRecognition
import com.zywczas.myworkout.watch.adapters.*
import com.zywczas.myworkout.watch.databinding.ActivityWeeksListBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class WeeksListActivity : BaseActivity() {

    companion object {
        const val KEY_WEEK_ID = "KEY_WEEK_ID"
    }

    private val viewModel: WeeksListViewModel by viewModels { viewModelFactory }
    private var binding: ActivityWeeksListBinding by autoRelease()
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }

    private val voiceRecognitionLauncher = registerVoiceRecognition { weekName -> viewModel.addNewWeek(weekName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeeksListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.weeksList.setup()
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun WearableRecyclerView.setup(){
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@WeeksListActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers(){
        viewModel.message.observe(this){ showToast(it) }
        viewModel.weeksElements.observe(this){ weeks -> FastAdapterDiffUtil.set(itemAdapter, weeks.toAdapterItems(), DiffUtilCallback()) }
        viewModel.isEmptyPlanMessageVisible.observe(this){ binding.emptyPlanMessage.isVisible = it }
    }

    private fun List<WeeksElements>.toAdapterItems(): List<GenericItem> = map {
        when(it){
            is WeeksElements.Title -> TitleItem(getString(it.title))
            is WeeksElements.Week -> WeekItem(it){ id -> goToWeekActivity(id) }
            is WeeksElements.AddNewWeek -> SettingsItem(getString(it.title)){ addNewWeek() }
//            is WeeksElements.Settings -> SettingsFooterItem{ goToSettingsActivity() } //todo na razie chyba nie potrzebne
        }
    }

    private fun goToWeekActivity(weekId: Long){
        val intent = Intent(this, SettingsDaysActivity::class.java).apply { //todo zmienic nazwe actywnosci
            putExtra(KEY_WEEK_ID, weekId)
        }
        startActivity(intent)
    }

    private fun addNewWeek(){
        voiceRecognitionLauncher.launch()
    }

//    private fun goToSettingsActivity(){//todo na razie chyba nie potrzebne
//        val intent = Intent(this, SettingsMainActivity::class.java)
//        startActivity(intent)
//    }

    private fun setupOnClickListeners(){
        binding.emptyPlanMessage.setOnClickListener { voiceRecognitionLauncher.launch() } //todo zmienic napis na dodaj pierwszy tydzien treningowy i przejsc do nazwy tygodnia
    }

}