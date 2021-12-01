package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.Week
import com.zywczas.myworkout.watch.databinding.ItemWeekBinding

class WeekItem(val week: Week) : AbstractBindingItem<ItemWeekBinding>() {

    override val type: Int = R.id.weekItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemWeekBinding =
        ItemWeekBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemWeekBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.weekName.text = week.name
    }

}