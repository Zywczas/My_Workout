package com.zywczas.myworkout.watch.activities.trainingplan.addexercise.domain

interface AddExerciseRepository {

    suspend fun saveExercise(dayId: Long, name: String, sets: Int, reps: String, weight: Double)

}