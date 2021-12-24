package com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain

interface ExerciseRepository {

    suspend fun getExercise(id: Long): Exercise

}