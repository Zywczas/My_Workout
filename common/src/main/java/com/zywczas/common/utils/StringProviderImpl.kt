package com.zywczas.common.utils

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class StringProviderImpl @Inject constructor(private val context: Context) : StringProvider {

    override suspend fun getString(resString: Int): String = context.getString(resString)

    override suspend fun getString(@StringRes resString: Int, arg: String): String = context.getString(resString, arg)

}