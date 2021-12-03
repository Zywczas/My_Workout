package com.zywczas.myworkout.watch.adapters

import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.diff.DiffCallback

class DiffUtilCallback : DiffCallback<GenericItem> {

    override fun getChangePayload(oldItem: GenericItem, oldItemPosition: Int, newItem: GenericItem, newItemPosition: Int): Any = newItem

    override fun areItemsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        when {
            oldItem is WeekItem && newItem is WeekItem -> oldItem.week.id == newItem.week.id
            oldItem is SettingsItem && newItem is SettingsItem -> true
            oldItem is TitleItem && newItem is TitleItem -> true
            else -> false
        }

    override fun areContentsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
            when (oldItem) {
                is WeekItem -> oldItem.week == (newItem as WeekItem).week
                is SettingsItem -> true
                is TitleItem -> oldItem.title == (newItem as TitleItem).title
                else -> false
            }

}