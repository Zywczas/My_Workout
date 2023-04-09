package com.zywczas.myworkout.uicomponents

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.zywczas.common.utils.Action

@Composable
fun BaseMessageDialog(
    title: String,
    message: String,
    onDismissRequest: Action,
    buttons: @Composable () -> Unit
) {
    BaseDialog(
        title = title,
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.subtitle1
            )
        },
        onDismissRequest = onDismissRequest,
        buttons = buttons
    )
}
