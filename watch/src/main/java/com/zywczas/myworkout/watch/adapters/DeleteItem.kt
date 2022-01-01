package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ItemDeleteBinding

class DeleteItem(val title: String, private val onClickAction: ()->Unit) : AbstractBindingItem<ItemDeleteBinding>() {

    override val type: Int = R.id.deleteItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemDeleteBinding =
        ItemDeleteBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemDeleteBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.title.text = title
        binding.root.setOnClickListener { onClickAction() }
    }

}