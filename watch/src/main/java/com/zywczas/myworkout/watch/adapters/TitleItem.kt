package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ItemTitleBinding

class TitleItem(val title: String) : AbstractBindingItem<ItemTitleBinding>() {

    override val type: Int = R.id.titleItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemTitleBinding =
        ItemTitleBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemTitleBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.title.text = title
    }

}
