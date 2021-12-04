package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ItemSettingsFooterBinding

class SettingsFooterItem(private val onClickAction: ()->Unit) : AbstractBindingItem<ItemSettingsFooterBinding>() {

    override val type: Int = R.id.settingsFooterItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemSettingsFooterBinding =
        ItemSettingsFooterBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemSettingsFooterBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.root.setOnClickListener { onClickAction() }
    }

}