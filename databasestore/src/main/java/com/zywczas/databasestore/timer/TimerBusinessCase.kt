package com.zywczas.databasestore.timer

import com.zywczas.databasestore.timer.entities.TimerEntity

interface TimerBusinessCase {

    suspend fun getTimer(): TimerEntity

}