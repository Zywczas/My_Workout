package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

interface WeeksListRepository {

    suspend fun getWeeks(): List<WeeksElements.Week>

    suspend fun saveNewWeek(week: WeeksElements.Week)

}