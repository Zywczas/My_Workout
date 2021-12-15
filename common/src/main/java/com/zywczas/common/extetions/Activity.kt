package com.zywczas.common.extetions

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes

fun Activity.showToast(@StringRes text: Int) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
fun Activity.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()