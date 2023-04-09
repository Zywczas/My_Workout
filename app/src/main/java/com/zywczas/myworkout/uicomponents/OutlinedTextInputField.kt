package com.zywczas.myworkout.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zywczas.common.utils.OnTextProvided
import com.zywczas.myworkout.R
import com.zywczas.myworkout.theme.Colors
import com.zywczas.myworkout.theme.Spacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutlinedTextInputField(
    hint: String,
    isError: Boolean,
    errorText: String,
    onValueChange: OnTextProvided,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
            label = { Text(hint) },
            singleLine = true,
            isError = isError,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = Colors.darkGrey,
                disabledTextColor = Colors.darkGrey
            ),
            trailingIcon = {
                if (isError) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_error),
                        tint = MaterialTheme.colors.error,
                        contentDescription = null,
                    )
                }
            },
            keyboardActions = KeyboardActions {
                keyboardController?.hide()
            },
            modifier = modifier
        )
        if (isError) {
            Spacer(Modifier.height(Spacing.xxs))
            Text(
                text = errorText,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(horizontal = Spacing.s)
            )
        }
    }
}
