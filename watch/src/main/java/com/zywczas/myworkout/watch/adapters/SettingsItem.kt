package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ItemSettingsBinding

class SettingsItem(private val onClickAction: ()->Unit) : AbstractBindingItem<ItemSettingsBinding>() {

    override val type: Int = R.id.settingsItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemSettingsBinding =
            ItemSettingsBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemSettingsBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.root.setOnClickListener { onClickAction() }

    }

}