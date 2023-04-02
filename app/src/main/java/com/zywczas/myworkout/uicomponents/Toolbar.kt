package com.zywczas.myworkout.uicomponents

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zywczas.myworkout.R
import com.zywczas.myworkout.theme.AppTheme

@Composable
fun Toolbar(@StringRes title: Int, modifier: Modifier = Modifier) {
    val appBarHeight = 56.dp
    Surface(
        modifier = modifier.height(appBarHeight),
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
        TopAppBar(
            title = { Text(stringResource(title)) },
            backgroundColor = Color.Transparent,
            contentColor = Color.White,
            elevation = 0.dp
        )
    }
}

@Preview(name = "TopAppBar")
@Composable
private fun Preview() {
    AppTheme {
        Toolbar(R.string.title_training_weeks)
    }
}
