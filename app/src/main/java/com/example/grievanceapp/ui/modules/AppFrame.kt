package com.example.grievanceapp.ui.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun AppFrame(
    onHelp: () -> Unit,
    onLightDarkClick: () -> Unit,
    onThemeSelectionClick: () -> Unit,
    onLogOut: () -> Unit,
    isAdmin: Boolean,
    modifier: Modifier = Modifier,
    headerMessage: String = "Place Holder Text Welcoming A User To The App",
    content: @Composable ColumnScope.() -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Closes the drawer, then runs whatever the menu item should do
    fun closeThen(action: () -> Unit): () -> Unit = {
        scope.launch { drawerState.close() }
        action()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = modifier,
        drawerContent = {
            AppDrawer(
                onCloseClick = closeThen {},
                onHelpClick = closeThen(onHelp),
                onLightDarkClick = closeThen(onLightDarkClick),
                onThemeSelectionClick = closeThen(onThemeSelectionClick),
                onLogOutClick = closeThen(onLogOut),
                showThemeSelection = isAdmin
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.navigationBarsPadding()) {
                AppHeader(
                    message = headerMessage,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
                content()
            }
        }
    }
}