package com.zywczas.common.extetions

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addVerticalItemDivider(@DrawableRes divider: Int) {
    val itemDivider = DividerItemDecoration(context, RecyclerView.VERTICAL)
    ContextCompat.getDrawable(context, divider)?.let {
        itemDivider.setDrawable(it)
    }
    addItemDecoration(itemDivider)
}