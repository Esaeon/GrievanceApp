package com.example.grievanceapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.modules.AppFrame
import com.example.grievanceapp.ui.modules.BackButton
import com.example.grievanceapp.ui.modules.ContactCard
import com.example.grievanceapp.ui.theme.GrievanceAppTheme

data class Contact(val name: String, val phone: String)

// Stand-in data matching the Figma frame, replaced later by real contacts
val placeholderContacts = List(6) { index ->
    Contact(name = "Teacher ${index + 1}", phone = "Phone Number ${index + 1}")
}

@Composable
fun ContactsScreen(
    contacts: List<Contact>,
    onBack: () -> Unit,
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
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(contacts) { contact ->
                ContactCard(name = contact.name, phone = contact.phone)
            }
        }
        BackButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
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
fun ContactsScreenPreview() {
    GrievanceAppTheme {
        ContactsScreen(placeholderContacts, {}, {}, {}, {}, {}, isAdmin = true)
    }
}