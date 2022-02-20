package com.zywczas.myworkout.screens.trainingplan.weekslist.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import javax.inject.Inject

class WeeksListRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : WeeksListRepository {

    override suspend fun getWeeks(): List<Week> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewWeek(name: String) {
        TODO("Not yet implemented")
    }

}