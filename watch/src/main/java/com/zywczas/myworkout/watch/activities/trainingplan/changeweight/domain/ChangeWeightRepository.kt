package com.zywczas.myworkout.watch.activities.trainingplan.changeweight.domain

interface ChangeWeightRepository {

    suspend fun getExercise(id: Long): Exercise

    suspend fun saveWeight(weight: Double)

}