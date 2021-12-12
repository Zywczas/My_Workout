package com.zywczas.myworkout.watch.adapters

import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.diff.DiffCallback

class DiffUtilCallback : DiffCallback<GenericItem> {

    override fun getChangePayload(oldItem: GenericItem, oldItemPosition: Int, newItem: GenericItem, newItemPosition: Int): Any = newItem

    override fun areItemsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        when {
            oldItem is WeekItem && newItem is WeekItem -> oldItem.week.id == newItem.week.id
            oldItem is SettingsFooterItem && newItem is SettingsFooterItem -> true
            oldItem is TitleItem && newItem is TitleItem -> true
            oldItem is SettingsItem && newItem is SettingsItem -> oldItem.title == newItem.title
            oldItem is SettingsWeekItem && newItem is SettingsWeekItem -> oldItem.week.id == newItem.week.id
            else -> false
        }

    override fun areContentsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        when (oldItem) {
            is WeekItem -> oldItem.week == (newItem as WeekItem).week
            is SettingsFooterItem -> true
            is TitleItem -> oldItem.title == (newItem as TitleItem).title
            is SettingsItem -> oldItem.title == (newItem as SettingsItem).title
            is SettingsWeekItem -> oldItem.week == (newItem as SettingsWeekItem).week
            else -> false
        }

}