package com.zywczas.myworkout.watch.services.timer

interface TimerServiceRepository {

    suspend fun getBreakPeriodInSeconds(): Int

}