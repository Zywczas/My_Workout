package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

interface WeeksListRepository {

    suspend fun getWeeks(): List<WeeksListElements.Week>

    suspend fun saveNewWeek(week: WeeksListElements.Week)

}