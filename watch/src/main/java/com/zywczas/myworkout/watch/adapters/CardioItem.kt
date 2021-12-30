package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.ItemCardioBinding

class CardioItem : AbstractBindingItem<ItemCardioBinding>() {

    override val type: Int = R.id.cardioItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemCardioBinding =
        ItemCardioBinding.inflate(inflater, parent, false)

}