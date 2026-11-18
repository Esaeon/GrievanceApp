package com.example.grievanceapp.ui.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.theme.GrievanceAppTheme
import com.example.grievanceapp.ui.theme.ThemeMode

@Composable
fun ThemeModeDialog(
    current: ThemeMode,
    onSelect: (ThemeMode) -> Unit,
    onDismiss: () -> Unit
) {
    val scheme = MaterialTheme.colorScheme

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = scheme.surface,
        titleContentColor = scheme.onSurface,
        textContentColor = scheme.onSurface,
        title = { Text("Light/Dark Mode") },
        text = {
            Column(modifier = Modifier.selectableGroup()) {
                ThemeMode.entries.forEach { mode ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = mode == current,
                                onClick = { onSelect(mode) },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 12.dp)
                    ) {
                        RadioButton(
                            selected = mode == current,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = scheme.onSurface,
                                unselectedColor = scheme.onSurface
                            )
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(mode.label)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close", color = scheme.onSurface)
            }
        }
    )
}

@Preview
@Composable
fun ThemeModeDialogPreview() {
    GrievanceAppTheme {
        ThemeModeDialog(current = ThemeMode.System, onSelect = {}, onDismiss = {})
    }
}