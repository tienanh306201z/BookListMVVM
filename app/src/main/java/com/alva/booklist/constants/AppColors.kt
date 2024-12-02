package com.alva.booklist.constants

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object AppColors {
    val successColor = Color(0xFF00C17C)
    val warningColor = Color(0xFFF9B304)
    val errorColor = Color(0xFFFB4772)
    val neutralColor = Color(0xFF5385EE)

    val systemLightColorScheme = lightColorScheme().copy(
        primary = Color(0xFF1B1B43),
        onPrimary = Color.White,
        secondary = Color(0xFF4F4F98),
        onSecondary = Color.White,
        tertiary = Color(0xFF8181EF),
        onTertiary = Color.White,
    )

    val systemDarkColorScheme = darkColorScheme().copy(
        primary = Color(0xFF1B1B43),
        onPrimary = Color.White,
        secondary = Color(0xFF4F4F98),
        onSecondary = Color.White,
        tertiary = Color(0xFF8181EF),
        onTertiary = Color.White,
    )
}