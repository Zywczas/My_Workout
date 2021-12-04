package com.zywczas.myworkout.watch.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ItemWeekBinding

class WeekItem(val week: Week, private val onClickAction:(Long)->Unit) : AbstractBindingItem<ItemWeekBinding>() {

    override val type: Int = R.id.weekItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemWeekBinding =
            ItemWeekBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemWeekBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.weekName.text = week.name
        if (week.displayedDates.isNotBlank()) {
            binding.dates.text = week.displayedDates
        } else {
            binding.dates.isVisible = false
        }
        if (week.isFinished) {
            binding.weekName.setTextColor(binding.root.context.getColor(R.color.exerciseDoneText))
            binding.dates.setTextColor(binding.root.context.getColor(R.color.exerciseDoneText))
            binding.backgroundLayout.backgroundTintList = ColorStateList.valueOf(binding.root.context.getColor(R.color.exerciseDoneBackground))
        }
        binding.root.setOnClickListener { onClickAction(week.id) }
    }

}