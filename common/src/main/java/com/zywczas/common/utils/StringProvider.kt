package com.zywczas.common.utils

import androidx.annotation.StringRes

interface StringProvider {

    suspend fun getString(@StringRes resString: Int, vararg args: Any): String

}