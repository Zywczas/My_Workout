package com.zywczas.myworkout.watch.activities.trainingplan.week.domain

interface WeekRepository {

    suspend fun getDays(weekId: Long): List<DaysElements.Day>

}