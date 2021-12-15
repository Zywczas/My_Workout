package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.DaysElements
import com.zywczas.myworkout.watch.databinding.ItemWeekHeaderBinding

class WeekHeaderItem(val week: DaysElements.WeekHeader) : AbstractBindingItem<ItemWeekHeaderBinding>() {

    override val type: Int = R.id.weekHeaderItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemWeekHeaderBinding =
        ItemWeekHeaderBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemWeekHeaderBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.title.text = week.weekName
        if (week.displayedDate.isNotBlank()){
            binding.date.text = week.displayedDate
        } else {
            binding.date.isVisible = false
        }
    }

}