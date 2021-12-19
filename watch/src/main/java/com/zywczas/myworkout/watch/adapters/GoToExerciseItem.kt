package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ItemGoToExerciseBinding

class GoToExerciseItem(val title: String, private val onClickAction: ()->Unit) : AbstractBindingItem<ItemGoToExerciseBinding>() {

    override val type: Int = R.id.goToExerciseItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemGoToExerciseBinding =
        ItemGoToExerciseBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemGoToExerciseBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.title.text = title
        binding.root.setOnClickListener { onClickAction() }
    }

}