package com.example.grievanceapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.modules.AppFrame
import com.example.grievanceapp.ui.modules.MenuCard
import com.example.grievanceapp.ui.theme.GrievanceAppTheme

@Composable
fun HomeScreen(
    onFileComplaint: () -> Unit,
    onMakeSuggestion: () -> Unit,
    onContacts: () -> Unit,
    onHelp: () -> Unit,
    onLightDarkClick: () -> Unit,
    onThemeSelectionClick: () -> Unit,
    onLogOut: () -> Unit,
    isAdmin: Boolean,
    modifier: Modifier = Modifier
) {
    AppFrame(
        onHelp = onHelp,
        onLightDarkClick = onLightDarkClick,
        onThemeSelectionClick = onThemeSelectionClick,
        onLogOut = onLogOut,
        isAdmin = isAdmin,
        modifier = modifier
    ) {
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
        HomeScreen({}, {}, {}, {}, {}, {}, {}, isAdmin = true)
    }
}