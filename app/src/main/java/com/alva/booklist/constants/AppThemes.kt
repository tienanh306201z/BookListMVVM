package com.alva.booklist.constants

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.alva.booklist.MainActivity

/**
 * Sets the Material theme for the application, with support for dark mode.
 *
 * @param isDarkMode Boolean flag to indicate if dark mode is enabled. Defaults to system setting.
 * @param content Composable content to be displayed within the theme.
 */
@Composable
fun AppMaterialTheme(isDarkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val context = LocalContext.current as MainActivity
    val view = LocalView.current

    LaunchedEffect(key1 = isDarkMode) {
        WindowCompat.getInsetsController(context.window, view).apply {
            isAppearanceLightNavigationBars = !isDarkMode
            isAppearanceLightStatusBars = !isDarkMode
        }
    }

    MaterialTheme(
        typography = AppFonts.appTypography,
        colorScheme = (if (isDarkMode) AppColors.systemDarkColorScheme else AppColors.systemLightColorScheme).switch(),
        content = content
    )
}

/**
 * Extension function to determine if the current color scheme is in dark mode.
 *
 * @return Boolean indicating if the color scheme is dark.
 */
@Composable
fun ColorScheme.isDarkMode() = this.background.luminance() <= 0.5

@Composable
private fun animateColor(targetValue: Color) = animateColorAsState(
    targetValue = targetValue,
    animationSpec = tween(durationMillis = 300),
    label = ""
).value

@Composable
private fun ColorScheme.switch() = copy(
    primary = animateColor(targetValue = primary),
    onPrimary = animateColor(targetValue = onPrimary),
    primaryContainer = animateColor(targetValue = primaryContainer),
    onPrimaryContainer = animateColor(targetValue = onPrimaryContainer),
    inversePrimary = animateColor(targetValue = inversePrimary),
    secondary = animateColor(targetValue = secondary),
    onSecondary = animateColor(targetValue = onSecondary),
    secondaryContainer = animateColor(targetValue = secondaryContainer),
    onSecondaryContainer = animateColor(targetValue = onSecondaryContainer),
    tertiary = animateColor(targetValue = tertiary),
    onTertiary = animateColor(targetValue = onTertiary),
    tertiaryContainer = animateColor(targetValue = tertiaryContainer),
    onTertiaryContainer = animateColor(targetValue = onTertiaryContainer),
    background = animateColor(targetValue = background),
    onBackground = animateColor(targetValue = onBackground),
    surface = animateColor(targetValue = surface),
    onSurface = animateColor(targetValue = onSurface),
    surfaceVariant = animateColor(targetValue = surfaceVariant),
    onSurfaceVariant = animateColor(targetValue = onSurfaceVariant),
    surfaceTint = animateColor(targetValue = surfaceTint),
    inverseSurface = animateColor(targetValue = inverseSurface),
    inverseOnSurface = animateColor(targetValue = inverseOnSurface),
    error = animateColor(targetValue = error),
    onError = animateColor(targetValue = onError),
    errorContainer = animateColor(targetValue = errorContainer),
    onErrorContainer = animateColor(targetValue = onErrorContainer),
    outline = animateColor(targetValue = outline),
    outlineVariant = animateColor(targetValue = outlineVariant),
    scrim = animateColor(targetValue = scrim),
    surfaceBright = animateColor(targetValue = surfaceBright),
    surfaceContainer = animateColor(targetValue = surfaceContainer),
    surfaceContainerHigh = animateColor(targetValue = surfaceContainerHigh),
    surfaceContainerHighest = animateColor(targetValue = surfaceContainerHighest),
    surfaceContainerLow = animateColor(targetValue = surfaceContainerLow),
    surfaceContainerLowest = animateColor(targetValue = surfaceContainerLowest),
    surfaceDim = animateColor(targetValue = surfaceDim),
)