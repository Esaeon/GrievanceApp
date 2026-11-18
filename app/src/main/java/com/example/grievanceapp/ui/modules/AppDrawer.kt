package com.example.grievanceapp.ui.modules

import androidx.compose.foundation.layout.width
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.theme.GrievanceAppTheme

@Composable
fun AppDrawer(
    onCloseClick: () -> Unit,
    onHelpClick: () -> Unit,
    onThemeClick: () -> Unit,
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(
        modifier = modifier.width(230.dp),
        drawerShape = RectangleShape,
        drawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
        drawerContentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        IconButton(onClick = onCloseClick) {
            Icon(Icons.Filled.Menu, contentDescription = "Close menu")
        }
        DrawerRow("Help", Icons.AutoMirrored.Filled.Help, onHelpClick)
        DrawerRow("Theme Selection", Icons.Filled.Colorize, onThemeClick)
        DrawerRow("Log Out", Icons.AutoMirrored.Filled.Logout, onLogOutClick)
    }
}

@Composable
private fun DrawerRow(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    val contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    NavigationDrawerItem(
        label = { Text(label) },
        icon = { Icon(icon, contentDescription = null) },
        selected = false,
        onClick = onClick,
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor = Color.Transparent,
            unselectedIconColor = contentColor,
            unselectedTextColor = contentColor
        )
    )
}

@Preview(showBackground = true, heightDp = 640)
@Composable
fun AppDrawerPreview() {
    GrievanceAppTheme {
        AppDrawer(
            onCloseClick = {},
            onHelpClick = {},
            onThemeClick = {},
            onLogOutClick = {}
        )
    }
}