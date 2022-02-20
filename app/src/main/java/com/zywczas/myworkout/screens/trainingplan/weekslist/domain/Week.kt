package com.zywczas.myworkout.screens.trainingplan.weekslist.domain

import org.joda.time.DateTime

data class Week(
    val id: Long = 0L,
    var name: String = "",
    val sequence: Int = 0,
    val copyVersion: Int = 1,
    val dateStarted: DateTime? = null,
    val dateFinished: DateTime? = null,
    val isFinished: Boolean = false,
    var displayedDates: String = ""
)