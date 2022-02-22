package com.zywczas.myworkout.screens.trainingplan.weekslist.domain

interface WeeksListRepository {

    suspend fun getWeeks(): List<Week>

    suspend fun deleteWeek(id: Long)

    suspend fun saveNewWeek(name: String)

}