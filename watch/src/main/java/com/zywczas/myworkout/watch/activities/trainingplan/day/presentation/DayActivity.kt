package com.zywczas.myworkout.watch.activities.trainingplan.day.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.zywczas.common.extetions.showToast
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.activities.trainingplan.week.presentation.WeekActivity
import com.zywczas.myworkout.watch.activityresultcontracts.registerVoiceRecognition
import com.zywczas.myworkout.watch.adapters.DiffUtilCallback
import com.zywczas.myworkout.watch.databinding.ActivityDayBinding
import com.zywczas.myworkout.watch.utils.CustomScrollingLayoutCallback

class DayActivity : BaseActivity() {

    private var binding: ActivityDayBinding by autoRelease()
    private val viewModel: DayViewModel by viewModels{ viewModelFactory }
    private val itemAdapter by lazy { ItemAdapter<GenericItem>() }
    private val dayId by lazy { intent?.getLongExtra(WeekActivity.KEY_DAY_ID, 0) ?: 0L }
    private val voiceRecognitionLauncher = registerVoiceRecognition { exerciseName -> viewModel.addNewExercise(exerciseName, dayId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.exerciseList.setup()
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun WearableRecyclerView.setup(){
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@DayActivity, CustomScrollingLayoutCallback())
        adapter = FastAdapter.with(itemAdapter)
    }

    private fun setupLiveDataObservers(){
        viewModel.message.observe(this){ showToast(it) }
        viewModel.isEmptyPlanMessageGone.observe(this){ binding.emptyPlanMessage.isGone = it }
//        viewModel.daysElements.observe(this){ days -> FastAdapterDiffUtil.set(itemAdapter, days.toAdapterItems(), DiffUtilCallback()) }
    }


    private fun setupOnClickListeners(){
        binding.emptyPlanMessage.setOnClickListener { voiceRecognitionLauncher.launch() }
    }

}