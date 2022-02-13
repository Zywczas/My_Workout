package com.zywczas.myworkout.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.zywczas.common.theme.DarkColorsPalette
import com.zywczas.common.theme.LightColorsPalette

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColorsPalette else LightColorsPalette, //todo przetestowac czy dobrze przelacza theme'y
        content = content
    )
}