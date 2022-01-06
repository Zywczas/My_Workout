package com.zywczas.myworkout.watch.activities.trainingplan.timer.domain

data class NextExercise(
    val id: Long = 0,
    val dayId: Long = -1,
    val name: String = "",
    val sequence: Int = 0,
    val setsQuantity: Int = 0,
    var nextSet: Int = 1,
    val repsQuantity: String = "",
    val weight: Double = 0.0
)