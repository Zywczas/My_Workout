package com.zywczas.myworkout.watch.activities.trainingplan.timer.domain


interface TimerRepository {

    suspend fun getNextExercise(id: Long): Exercise

}