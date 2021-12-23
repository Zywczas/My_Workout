package com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain

interface ExerciseRepository {

    suspend fun getCurrentExercise(id: Long): Exercise

}