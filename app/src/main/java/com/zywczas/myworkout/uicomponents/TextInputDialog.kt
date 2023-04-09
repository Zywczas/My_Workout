package com.zywczas.myworkout.uicomponents

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zywczas.common.utils.Action
import com.zywczas.common.utils.OnTextProvided
import com.zywczas.myworkout.R
import com.zywczas.myworkout.theme.Spacing

@Composable
fun TextInputDialog(
    title: String,
    onDismissRequest: Action,
    hint: String,
    isError: Boolean,
    errorText: String,
    onTextChanged: OnTextProvided,
    onDismissClick: Action,
    onConfirmClick: OnTextProvided,
) {
    var text by remember { mutableStateOf("") }
    BaseDialog(
        title = null,
        text = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h2
                )
                Spacer(Modifier.height(Spacing.xs))
                OutlinedTextInputField(
                    hint = hint,
                    isError = isError,
                    errorText = errorText,
                    onValueChange = {
                        text = it
                        onTextChanged(it)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        onDismissRequest = onDismissRequest,
        buttons = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Spacing.s, start = Spacing.s, end = Spacing.s)
            ) {
                DialogButton(text = stringResource(R.string.button_cancel), onClick = onDismissClick)
                Spacer(Modifier.width(Spacing.xxs))
                DialogButton(text = stringResource(R.string.button_add), onClick = { onConfirmClick(text) })
            }
        }
    )
}
