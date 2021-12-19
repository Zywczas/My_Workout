package com.zywczas.myworkout.watch.activities.trainingplan.addexercise.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import javax.inject.Inject

class AddExerciseRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : AddExerciseRepository {

    override suspend fun saveExercise(dayId: Long, name: String, sets: Int, reps: String, weight: Double) = trainings.saveExercise(dayId, name, sets, reps, weight)

}