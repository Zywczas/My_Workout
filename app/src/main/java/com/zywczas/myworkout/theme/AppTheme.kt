package com.zywczas.myworkout.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zywczas.myworkout.R

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColorsPalette else LightColorsPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

private val DarkColorsPalette = darkColors(
    primary = Colors.Primary.darkMode,
    primaryVariant = Colors.logoFirst,
    secondary = Colors.Primary.darkMode
)

private val LightColorsPalette = lightColors(
    primary = Colors.Primary.lightMode,
    primaryVariant = Colors.Primary.lightMode,
    secondary = Colors.Primary.lightMode
)

private val fontFamily = FontFamily(
    listOf(
        Font(R.font.ubuntu_regular, FontWeight.Normal),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
        Font(R.font.ubuntu_bold, FontWeight.Bold),
    )
)

private val typography = Typography(
    //Dialog title
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        letterSpacing = 0.37.sp,
        lineHeight = 20.sp,
        fontFamily = fontFamily
    ),
    //Dialog text
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        letterSpacing = 0.51.sp,
        lineHeight = 18.sp,
        fontFamily = fontFamily
    )
)

private val shapes = Shapes(
    small = RoundedCornerShape(16.dp),
    medium = RoundedCornerShape(16.dp)
)
