package com.zywczas.common.utils

interface DateTimeProvider {

//    suspend fun now(): DateTime //todo nie uzywane
    suspend fun getTimerRepresentationOf(seconds: Int): String

}