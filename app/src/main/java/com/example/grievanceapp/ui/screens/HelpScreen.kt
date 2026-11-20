package com.example.grievanceapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.modules.AppFrame
import com.example.grievanceapp.ui.modules.BackButton
import com.example.grievanceapp.ui.modules.HelpEntry
import com.example.grievanceapp.ui.modules.HelpSectionHeader
import com.example.grievanceapp.ui.modules.HelpTerm
import com.example.grievanceapp.ui.theme.GrievanceAppTheme

@Composable
fun HelpScreen(
    onBack: () -> Unit,
    onHelp: () -> Unit,
    onLightDarkClick: () -> Unit,
    onThemeSelectionClick: () -> Unit,
    onLogOut: () -> Unit,
    isAdmin: Boolean,
    modifier: Modifier = Modifier,
    schoolName: String = "[YOUR ASSIGNED SCHOOL HERE]"
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            HelpSectionHeader("Main Page")

            HelpEntry(
                title = "File A Complaint & Make A Suggestion",
                description = "These allow you to make complaints or suggestions to $schoolName.",
                icon = Icons.Filled.ChatBubble
            )
            HelpTerm(
                term = "Complaints",
                meaning = "Report existing problems with previously-fine areas."
            )
            HelpTerm(
                term = "Suggestions",
                meaning = "Report possible improvements with areas that need improvement."
            )

            HelpEntry(
                title = "Contacts",
                description = "These are the contacts for your teachers at $schoolName. " +
                        "They change every semester.",
                icon = Icons.Filled.Person
            )

            Spacer(Modifier.height(8.dp))
            HelpSectionHeader("Side Bar")

            HelpEntry(
                title = "Help",
                description = "Brings you here.",
                icon = Icons.AutoMirrored.Filled.Help
            )
            HelpEntry(
                title = "Light/Dark Mode",
                description = "Changes the light/dark theme.",
                icon = Icons.Filled.Contrast
            )
            if (isAdmin) {
                HelpEntry(
                    title = "Theme Selection",
                    description = "Allows you to change the theme of the school within the database.",
                    icon = Icons.Filled.Colorize
                )
            }
            HelpEntry(
                title = "Log Out",
                description = "Logs you out of your current account.",
                icon = Icons.AutoMirrored.Filled.Logout
            )
        }

        BackButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(160.dp)
                .padding(vertical = 16.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 780,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HelpScreenAdminPreview() {
    GrievanceAppTheme {
        HelpScreen({}, {}, {}, {}, {}, isAdmin = true)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
fun HelpScreenStudentPreview() {
    GrievanceAppTheme {
        HelpScreen({}, {}, {}, {}, {}, isAdmin = false)
    }
}