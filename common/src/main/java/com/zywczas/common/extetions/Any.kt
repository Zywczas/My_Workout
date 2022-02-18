package com.zywczas.common.extetions

import android.util.Log
import kotlin.reflect.KClass

private const val TAG = "MyWorkoutTag"

fun Any.logD(e: Throwable?) = logD(e?.message)

fun Any.logD(msg: String?) = Log.d("$TAG in ${this.javaClass.simpleName}", "$msg")

fun <T : Any> Any.logD(clazz: KClass<T>, e: Throwable?) = logD(clazz, e?.message)

fun <T : Any> Any.logD(clazz: KClass<T>, msg: String?) = Log.d("$TAG in ${clazz.simpleName}", "$msg")

fun logD(e: Throwable?) = logD(e?.message)

fun logD(msg: String?) = Log.d(TAG, "$msg")