package com.zywczas.common.utils

import android.content.Context
import javax.inject.Inject

class StringProviderImpl @Inject constructor(private val context: Context) : StringProvider {

    override suspend fun getString(resString: Int, vararg args: Any): String = context.getString(resString, args)

}