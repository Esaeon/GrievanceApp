package com.example.grievanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.grievanceapp.ui.navigation.AppNavHost
import com.example.grievanceapp.ui.theme.GrievanceAppTheme
import com.example.grievanceapp.ui.theme.Primary
import com.example.grievanceapp.ui.theme.Secondary
import com.example.grievanceapp.ui.theme.ThemeMode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Light status bar icons in both themes, since the header is always green
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)

        setContent {
            var themeMode by remember {
                mutableStateOf(
                    runCatching {
                        ThemeMode.valueOf(prefs.getString("theme_mode", null) ?: "")
                    }.getOrDefault(ThemeMode.System)
                )
            }
            // Falls back to the defaults in Color.kt until an admin saves a choice
            var primaryColor by remember {
                mutableStateOf(Color(prefs.getInt("primary_color", Primary.toArgb())))
            }
            var secondaryColor by remember {
                mutableStateOf(Color(prefs.getInt("secondary_color", Secondary.toArgb())))
            }

            val darkTheme = when (themeMode) {
                ThemeMode.System -> isSystemInDarkTheme()
                ThemeMode.Light -> false
                ThemeMode.Dark -> true
            }

            GrievanceAppTheme(
                darkTheme = darkTheme,
                primary = primaryColor,
                secondary = secondaryColor
            ) {
                AppNavHost(
                    themeMode = themeMode,
                    onThemeModeChange = { newMode ->
                        themeMode = newMode
                        prefs.edit().putString("theme_mode", newMode.name).apply()
                    },
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    onColorsChange = { newPrimary, newSecondary ->
                        primaryColor = newPrimary
                        secondaryColor = newSecondary
                        prefs.edit()
                            .putInt("primary_color", newPrimary.toArgb())
                            .putInt("secondary_color", newSecondary.toArgb())
                            .apply()
                    }
                )
            }
        }
    }
}