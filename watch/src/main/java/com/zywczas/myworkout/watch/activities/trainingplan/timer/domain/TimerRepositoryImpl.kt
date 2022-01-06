package com.zywczas.myworkout.watch.activities.trainingplan.timer.domain

import com.zywczas.databasestore.trainings.TrainingsBusinessCase
import com.zywczas.databasestore.trainings.entities.ExerciseLocal
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
    private val trainings: TrainingsBusinessCase
) : TimerRepository {

    override suspend fun getNextExercise(id: Long): NextExercise = trainings.getExercise(id).toDomain()

    private fun ExerciseLocal.toDomain() = NextExercise(
        id = id,
        dayId = foreignDayId,
        name = name,
        sequence = sequence,
        setsQuantity = setsQuantity,
        repsQuantity = repsQuantity,
        weight = weight
    )

    override suspend fun save(exercise: NextExercise) = trainings.saveExercise(exercise.toLocal())

    private fun NextExercise.toLocal() = ExerciseLocal(
        id = id,
        foreignDayId = dayId,
        name = name,
        sequence = sequence,
        setsQuantity = setsQuantity,
        currentSet = nextSet,
        repsQuantity = repsQuantity,
        weight = weight
    )

}