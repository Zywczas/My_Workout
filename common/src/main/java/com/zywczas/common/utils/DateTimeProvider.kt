package com.zywczas.common.utils

import org.joda.time.DateTime

interface DateTimeProvider {

    suspend fun now(): DateTime
    suspend fun getTimerRepresentationOf(seconds: Int): String

}