package com.zywczas.common.extetions

import android.widget.TextView
import androidx.annotation.ColorRes

fun TextView.setColorOfText(@ColorRes color: Int){
    setTextColor(context.getColor(color))
}