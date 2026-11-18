package com.example.grievanceapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Primary,
    onPrimary = PureWhite,
    primaryContainer = Secondary,
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
    primary = Primary,
    onPrimary = PureWhite,
    primaryContainer = Secondary,
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
    primary: Color = Primary,
    secondary: Color = Secondary,
    content: @Composable () -> Unit
) {
    val base = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = base.copy(
            primary = primary,
            primaryContainer = secondary
        ),
        typography = Typography,
        content = content
    )
}