package com.zywczas.myworkout.uicomponents

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zywczas.myworkout.R
import com.zywczas.myworkout.theme.AppTheme

@Composable
fun TopAppBar(@StringRes title: Int, modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(stringResource(title)) },
        modifier = modifier,
    )
}

@Preview(name = "TopAppBar")
@Composable
fun PreviewTopAppBar(){
    AppTheme {
        TopAppBar(R.string.title_training_weeks)
    }
}