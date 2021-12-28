package com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain

data class Exercise(
    val id: Long = 0,
    val dayId: Long = -1,
    val weekId: Long = -1, //todo w innych klasach domenowych tez pousuwac "foreign"
    val name: String = "",
    val sequence: Int = 0,
    val setsQuantity: Int = 0,
    val currentSet: Int = 1,
    val repsQuantity: String = "",
    val weight: Double = 0.0,
    val isFinished: Boolean = false
)