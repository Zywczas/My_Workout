package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.settings.weeks.domain.SettingsWeeksElements
import com.zywczas.myworkout.watch.databinding.ItemSettingsWeekBinding

class SettingsWeekItem(val week: SettingsWeeksElements.Week, private val onClickAction: (Long)->Unit) : AbstractBindingItem<ItemSettingsWeekBinding>(){

    override val type: Int = R.id.settingsWeekItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemSettingsWeekBinding =
        ItemSettingsWeekBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemSettingsWeekBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.weekName.text = week.name
        binding.root.setOnClickListener { onClickAction(week.id) }
    }

}