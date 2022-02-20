package com.zywczas.myworkout.composeitems

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TopAppBar(@StringRes title: Int, modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(stringResource(title)) },
        modifier = modifier
    )
}