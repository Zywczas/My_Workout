package com.zywczas.myworkout.screens.trainingplan.weekslist.domain

interface WeeksListRepository {

    suspend fun getWeeks(): List<Week>

    suspend fun saveNewWeek(name: String)

}