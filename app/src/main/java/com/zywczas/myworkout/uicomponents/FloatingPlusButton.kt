package com.zywczas.myworkout.uicomponents

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import com.zywczas.common.utils.Action

@Composable
fun FloatingPlusButton(action: Action) {
    FloatingActionButton(
        onClick = action,
        content = {
            Icon(imageVector = Icons.Default.Add, null)
        }
    )
}
