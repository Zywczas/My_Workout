package com.zywczas.myworkout.watch.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ItemWeekOrDayBinding

class WeekOrDayItem(val weekOrDayId: Long, val title: String, val dates: String, val isFinished: Boolean, private val onClickAction:(Long)->Unit) : AbstractBindingItem<ItemWeekOrDayBinding>() {

    override val type: Int = R.id.weekOrDayItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemWeekOrDayBinding =
        ItemWeekOrDayBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemWeekOrDayBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.title.text = title
        if (dates.isNotBlank()) {
            binding.dates.text = dates
            binding.dates.isVisible = true
        } else {
            binding.dates.isVisible = false
        }
        if (isFinished) {
            binding.title.setTextColor(binding.root.context.getColor(R.color.exerciseDoneText))
            binding.dates.setTextColor(binding.root.context.getColor(R.color.exerciseDoneText))
            binding.weekOrDayItem.backgroundTintList = ColorStateList.valueOf(binding.root.context.getColor(R.color.exerciseDoneBackground))
        }
        binding.root.setOnClickListener { onClickAction(weekOrDayId) }
    }

}