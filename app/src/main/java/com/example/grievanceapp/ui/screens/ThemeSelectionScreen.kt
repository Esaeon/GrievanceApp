package com.example.grievanceapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.modules.CancelButton
import com.example.grievanceapp.ui.modules.ColorChoiceRow
import com.example.grievanceapp.ui.modules.SubmitButton
import com.example.grievanceapp.ui.modules.colorToHex
import com.example.grievanceapp.ui.modules.parseHexColor
import com.example.grievanceapp.ui.theme.GrievanceAppTheme
import com.example.grievanceapp.ui.theme.Primary
import com.example.grievanceapp.ui.theme.Secondary

@Composable
fun ThemeSelectionScreen(
    currentPrimary: Color,
    currentSecondary: Color,
    onCancel: () -> Unit,
    onSubmit: (primary: Color, secondary: Color) -> Unit,
    modifier: Modifier = Modifier
) {
    var primaryHex by rememberSaveable { mutableStateOf(colorToHex(currentPrimary)) }
    var secondaryHex by rememberSaveable { mutableStateOf(colorToHex(currentSecondary)) }

    val primary = parseHexColor(primaryHex)
    val secondary = parseHexColor(secondaryHex)

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .safeDrawingPadding()
                .padding(24.dp)
        ) {
            ColorChoiceRow(
                label = "Choose Primary Color",
                hex = primaryHex,
                onHexChange = { primaryHex = it },
                color = primary
            )
            Spacer(Modifier.height(40.dp))
            ColorChoiceRow(
                label = "Choose Secondary Color",
                hex = secondaryHex,
                onHexChange = { secondaryHex = it },
                color = secondary
            )
            Spacer(Modifier.height(40.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CancelButton(onClick = onCancel, modifier = Modifier.weight(1f))
                SubmitButton(
                    onClick = {
                        if (primary != null && secondary != null) {
                            onSubmit(primary, secondary)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
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
fun ThemeSelectionScreenPreview() {
    GrievanceAppTheme {
        ThemeSelectionScreen(
            currentPrimary = Primary,
            currentSecondary = Secondary,
            onCancel = {},
            onSubmit = { _, _ -> }
        )
    }
}