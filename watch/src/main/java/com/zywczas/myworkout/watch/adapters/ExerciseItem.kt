package com.zywczas.myworkout.watch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.trainingplan.day.domain.DayElements
import com.zywczas.myworkout.watch.databinding.ItemExerciseBinding

class ExerciseItem(val exercise: DayElements.Exercise) : AbstractBindingItem<ItemExerciseBinding>() {

    override val type: Int = R.id.settingsItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemExerciseBinding =
        ItemExerciseBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemExerciseBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.title.text = exercise.name
        if (exercise.isFinished) {
            binding.title.setTextColor(binding.root.context.getColor(R.color.exerciseDoneText))
        }
    }

}