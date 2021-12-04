package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain

interface WeeksListRepository {

    suspend fun getWeeks(): List<WeeksList.Week>

}