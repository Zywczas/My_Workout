package com.zywczas.common.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val ColorLightGrayDarkMode = Color(0xFF9A9797)
private val ColorDeepPurple900 = Color(0xFF311b92)

//todo pomyslec czy dac tu przyrostki DarkMode...
private val ColorPrimary = Color(0xFF0072F8)
private val ColorLogoFirst = Color(0xFF0E7DD4)
private val ColorLogoSecond = Color(0xFF00FF8B)
val ColorExerciseDoneTextDarkMode = ColorLightGrayDarkMode
val ColorExerciseDoneBackgroundDarkMode = Color(0xFF33374E)
val ColorSettingsItemBackground = Color(0xFF28950C)
val ColorDeleteItemBackground = ColorDeepPurple900

val DarkColorsPalette = darkColors(
    primary = ColorPrimary,
    primaryVariant = ColorPrimary,
    secondary = ColorLogoSecond
)

val LightColorsPalette = lightColors(
    primary = ColorPrimary,
    primaryVariant = ColorPrimary,
    secondary = ColorLogoSecond
)