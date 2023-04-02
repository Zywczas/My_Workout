package com.zywczas.myworkout.uicomponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import com.zywczas.myworkout.theme.Spacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BaseDialog(
    title: String,
    message: String,
    modifier: Modifier = Modifier.padding(horizontal = Spacing.s),
    buttons: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h2
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.subtitle1
            )
        },
        buttons = buttons,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    )
}
