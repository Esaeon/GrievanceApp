package com.example.grievanceapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.modules.AppDrawer
import com.example.grievanceapp.ui.modules.AppHeader
import com.example.grievanceapp.ui.modules.MenuCard
import com.example.grievanceapp.ui.theme.GrievanceAppTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onFileComplaint: () -> Unit,
    onMakeSuggestion: () -> Unit,
    onContacts: () -> Unit,
    onHelp: () -> Unit,
    onThemeSelection: () -> Unit,
    onLogOut: () -> Unit,
    modifier: Modifier = Modifier
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
                onThemeClick = closeThen(onThemeSelection),
                onLogOutClick = closeThen(onLogOut)
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                AppHeader(
                    message = "Place Holder Text Welcoming A User To The App",
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 32.dp)
                ) {
                    MenuCard("File A Complaint", Icons.Filled.ChatBubble, onFileComplaint)
                    MenuCard("Make A Suggestion", Icons.Filled.Forum, onMakeSuggestion)
                    MenuCard("Contacts", Icons.Filled.Person, onContacts)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreenPreview() {
    GrievanceAppTheme {
        HomeScreen({}, {}, {}, {}, {}, {})
    }
}