package com.zywczas.myworkout.uicomponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import com.zywczas.common.utils.Action
import com.zywczas.myworkout.theme.Spacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BaseDialog(
    title: String?,
    text: @Composable () -> Unit,
    onDismissRequest: Action,
    buttons: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.padding(horizontal = Spacing.s),
        title = title?.let {
            {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h2
                )
            }
        },
        text = text,
        buttons = buttons,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    )
}
