package com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.ExerciseEntity
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : ExerciseRepository {

    override suspend fun getExercise(id: Long): Exercise = trainings.getExercise(id).toDomain()

    private fun ExerciseEntity.toDomain() = Exercise(
        id = id,
        name = name,
        setsQuantity = setsQuantity,
        currentSet = currentSet,
        repsQuantity = repsQuantity,
        weight = weight
    )

}