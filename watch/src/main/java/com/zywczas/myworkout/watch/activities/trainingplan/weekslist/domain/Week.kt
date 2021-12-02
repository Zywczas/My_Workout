package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

import org.joda.time.DateTime

data class Week(val id: Long,
                val name: String,
                val sequence: Int,
                val dateStarted: DateTime?,
                val dateFinished: DateTime?,
                val isFinished: Boolean,
                var displayedDates: String = "")