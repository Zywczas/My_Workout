package com.zywczas.common.utils

import org.joda.time.DateTime

interface DateTimeProvider {

    suspend fun now(): DateTime
    suspend fun getTimerRepresentationOf(seconds: Int): String
    suspend fun year(): Int
    suspend fun lastYear(): Int
    suspend fun nextYear(): Int

}