package com.example.grievanceapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grievanceapp.auth.UserRole
import com.example.grievanceapp.auth.authenticate
import com.example.grievanceapp.ui.modules.ThemeModeDialog
import com.example.grievanceapp.ui.screens.ComplaintScreen
import com.example.grievanceapp.ui.screens.ContactsScreen
import com.example.grievanceapp.ui.screens.HomeScreen
import com.example.grievanceapp.ui.screens.LoginScreen
import com.example.grievanceapp.ui.screens.SuggestionScreen
import com.example.grievanceapp.ui.screens.ThemeSelectionScreen
import com.example.grievanceapp.ui.screens.placeholderContacts
import com.example.grievanceapp.ui.theme.ThemeMode
import com.example.grievanceapp.ui.screens.HelpScreen

object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
    const val COMPLAINT = "complaint"
    const val SUGGESTION = "suggestion"
    const val CONTACTS = "contacts"
    const val THEME_SELECTION = "theme_selection"
    const val HELP = "help"
}

@Composable
fun AppNavHost(
    themeMode: ThemeMode,
    onThemeModeChange: (ThemeMode) -> Unit,
    primaryColor: Color,
    secondaryColor: Color,
    onColorsChange: (primary: Color, secondary: Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    var showThemeDialog by rememberSaveable { mutableStateOf(false) }
    var role by rememberSaveable { mutableStateOf<UserRole?>(null) }
    val isAdmin = role == UserRole.Admin

    val openLightDark = { showThemeDialog = true }
    val openThemeSelection = { navController.navigate(Routes.THEME_SELECTION) }
    // launchSingleTop stops repeated taps from stacking several Help screens
    val openHelp = {
        navController.navigate(Routes.HELP) { launchSingleTop = true }
    }
    val logOut: () -> Unit = {
        role = null
        navController.navigate(Routes.LOGIN) {
            popUpTo(navController.graph.id) { inclusive = true }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        modifier = modifier
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLogin = { username, password ->
                    val result = authenticate(username, password)
                    if (result != null) {
                        role = result
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    }
                    result != null
                }
            )
        }
        composable(Routes.HOME) {
            HomeScreen(
                onFileComplaint = { navController.navigate(Routes.COMPLAINT) },
                onMakeSuggestion = { navController.navigate(Routes.SUGGESTION) },
                onContacts = { navController.navigate(Routes.CONTACTS) },
                onHelp = openHelp,
                onLightDarkClick = openLightDark,
                onThemeSelectionClick = { openThemeSelection() },
                onLogOut = logOut,
                isAdmin = isAdmin
            )
        }
        composable(Routes.COMPLAINT) {
            ComplaintScreen(
                onCancel = { navController.popBackStack() },
                onSubmit = { complaint ->
                    // store or send the complaint here later
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.SUGGESTION) {
            SuggestionScreen(
                onCancel = { navController.popBackStack() },
                onSubmit = { suggestion ->
                    // store or send the suggestion here later
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.CONTACTS) {
            ContactsScreen(
                contacts = placeholderContacts,
                onBack = { navController.popBackStack() },
                onHelp = {},
                onLightDarkClick = openLightDark,
                onThemeSelectionClick = { openThemeSelection() },
                onLogOut = logOut,
                isAdmin = isAdmin
            )
        }
        composable(Routes.THEME_SELECTION) {
            ThemeSelectionScreen(
                currentPrimary = primaryColor,
                currentSecondary = secondaryColor,
                onCancel = { navController.popBackStack() },
                onSubmit = { newPrimary, newSecondary ->
                    onColorsChange(newPrimary, newSecondary)
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.HELP) {
            HelpScreen(
                onBack = { navController.popBackStack() },
                onHelp = {}, // already on this screen, the drawer just closes
                onLightDarkClick = openLightDark,
                onThemeSelectionClick = { openThemeSelection() },
                onLogOut = logOut,
                isAdmin = isAdmin
            )
        }
    }

    if (showThemeDialog) {
        ThemeModeDialog(
            current = themeMode,
            onSelect = onThemeModeChange,
            onDismiss = { showThemeDialog = false }
        )
    }
}