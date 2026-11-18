package com.example.grievanceapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = UwpGreen,
    onPrimary = PureWhite,
    primaryContainer = UwpGreenDark,
    onPrimaryContainer = PureWhite,
    secondary = PureBlack,
    onSecondary = PureWhite,
    background = PureWhite,
    onBackground = PureBlack,
    surface = PureWhite,
    onSurface = PureBlack,
    surfaceVariant = LightCard,
    onSurfaceVariant = PureBlack,
    secondaryContainer = LightCard,
    onSecondaryContainer = PureBlack,
    outline = PureBlack,
    outlineVariant = IconTintLight
)

private val DarkColors = darkColorScheme(
    primary = UwpGreen,
    onPrimary = PureWhite,
    primaryContainer = UwpGreenDark,
    onPrimaryContainer = PureWhite,
    secondary = DarkCancel,
    onSecondary = PureWhite,
    background = DarkBackground,
    onBackground = DarkText,
    surface = DarkBackground,
    onSurface = DarkText,
    surfaceVariant = DarkCard,
    onSurfaceVariant = DarkText,
    secondaryContainer = DarkCard,
    onSecondaryContainer = PureWhite,
    outline = PureWhite,
    outlineVariant = IconTintDark
)

@Composable
fun GrievanceAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}