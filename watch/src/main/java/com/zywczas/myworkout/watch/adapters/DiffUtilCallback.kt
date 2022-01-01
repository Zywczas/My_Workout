package com.zywczas.myworkout.watch.adapters

import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.diff.DiffCallback

class DiffUtilCallback : DiffCallback<GenericItem> {

    override fun getChangePayload(oldItem: GenericItem, oldItemPosition: Int, newItem: GenericItem, newItemPosition: Int): Any = newItem

    override fun areItemsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        when {
            oldItem is WeekOrDayItem && newItem is WeekOrDayItem -> oldItem.weekOrDayId == newItem.weekOrDayId
            oldItem is SettingsFooterItem && newItem is SettingsFooterItem -> true
            oldItem is TitleItem && newItem is TitleItem -> true
            oldItem is SettingsItem && newItem is SettingsItem -> oldItem.title == newItem.title
            oldItem is WeekHeaderItem && newItem is WeekHeaderItem -> true
            oldItem is GoToExerciseItem && newItem is GoToExerciseItem -> true
            oldItem is ExerciseItem && newItem is ExerciseItem -> oldItem.exercise.id == newItem.exercise.id
            oldItem is CardioItem && newItem is CardioItem -> true
            oldItem is DeleteItem && newItem is DeleteItem -> oldItem.title == newItem.title
            else -> false
        }

    override fun areContentsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        when (oldItem) {
            is WeekOrDayItem -> {
                newItem as WeekOrDayItem
                oldItem.title == newItem.title
                        && oldItem.dates == newItem.dates
                        && oldItem.isFinished == newItem.isFinished
            }
            is SettingsFooterItem -> true
            is TitleItem -> oldItem.title == (newItem as TitleItem).title
            is SettingsItem -> true
            is WeekHeaderItem -> oldItem.week == (newItem as WeekHeaderItem).week
            is GoToExerciseItem -> oldItem.title == (newItem as GoToExerciseItem).title
            is ExerciseItem -> oldItem.exercise == (newItem as ExerciseItem).exercise
            is CardioItem -> true
            is DeleteItem -> true
            else -> false
        }

}