package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import android.os.Bundle
import androidx.activity.viewModels
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
    }

    private fun WearableRecyclerView.setup(){
        //todo sprobowac powrzucac do layoutu
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(this@WeeksListActivity, CustomScrollingLayoutCallback())
        adapter = recyclerAdapter
    }

//    private fun addSomeItems(){
//        val items = listOf<GenericItem>(
//            WeekItem(Week("tydzien 1")),
//            WeekItem(Week("tydzien 2")),
//            WeekItem(Week("tydzien 3")),
//            WeekItem(Week("baaaaaaaaaardzo dluuuuuugi tyyyyyydzien sas sda sadasd das sd dasd sd ")),
//            WeekItem(Week(" dsa ad asd sdsds ds ds g 5gffdsf sdf gfg dsfgsada  dao afjdspij fpdjfpsj  ")),
//            WeekItem(Week("tydzien 6")),
//            WeekItem(Week("tydzien 7")),
//        )
//        FastAdapterDiffUtil.set(itemAdapter, items, DiffUtilCallback())
//    }

}