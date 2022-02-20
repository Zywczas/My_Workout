package com.zywczas.myworkout.watch.activities.trainingplan.changeweight.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.ExerciseLocal
import javax.inject.Inject

class ChangeWeightRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : ChangeWeightRepository {

    override suspend fun getExercise(id: Long): Exercise = trainings.getExercise(id).toDomain()

    private fun ExerciseLocal.toDomain() = Exercise(
        id = id,
        weight = weight
    )

    override suspend fun saveWeight(exerciseId: Long, weight: Double) {
        trainings.saveWeight(exerciseId, weight)
    }

}