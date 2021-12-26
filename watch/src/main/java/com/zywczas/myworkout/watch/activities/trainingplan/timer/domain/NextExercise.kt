package com.zywczas.myworkout.watch.activities.trainingplan.timer.domain

data class NextExercise( //todo sprawdzic czego nie uzywam
    val id: Long = 0,
    val foreignDayId: Long = -1,
    val name: String = "",
    val sequence: Int = 0,
    val setsQuantity: Int = 0,
    val currentSet: Int = 1,
    val repsQuantity: String = "",
    val weight: Double = 0.0
)