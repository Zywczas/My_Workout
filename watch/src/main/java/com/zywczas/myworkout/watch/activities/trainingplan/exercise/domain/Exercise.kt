package com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain

data class Exercise(
    val id: Long = 0,
    val name: String = "",
    val setsQuantity: Int = 0,
    val currentSet: Int = 1,
    val repsQuantity: String = "",
    val weight: Double = 0.0
)