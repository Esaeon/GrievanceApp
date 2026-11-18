package com.example.grievanceapp.ui.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.theme.GrievanceAppTheme

@Composable
fun ColorChoiceRow(
    label: String,
    hex: String,
    onHexChange: (String) -> Unit,
    color: Color?,
    modifier: Modifier = Modifier
) {
    val scheme = MaterialTheme.colorScheme

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = scheme.onBackground
        )
        Spacer(Modifier.height(12.dp))
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = scheme.secondaryContainer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        // grey placeholder while the typed text is not a valid color
                        .background(color ?: scheme.outlineVariant)
                )
                TextEntryBox(
                    value = hex,
                    onValueChange = { onHexChange(it.take(7)) },
                    placeholder = "#RRGGBB",
                    singleLine = true,
                    modifier = Modifier.width(150.dp)
                )
            }
        }
        if (color == null) {
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Enter 6 hex digits, like #054605",
                color = scheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

// Accepts "054605" or "#054605". Returns null if the text is not a valid color.
fun parseHexColor(input: String): Color? {
    val digits = input.trim().removePrefix("#")
    if (digits.length != 6) return null
    if (!digits.all { it in "0123456789abcdefABCDEF" }) return null
    val value = digits.toLongOrNull(16) ?: return null
    return Color(0xFF000000L or value) // FF alpha, always fully opaque
}

fun colorToHex(color: Color): String =
    "#%06X".format(color.toArgb() and 0xFFFFFF)

@Preview(showBackground = true)
@Composable
fun ColorChoiceRowPreview() {
    GrievanceAppTheme {
        Surface {
            ColorChoiceRow(
                label = "Choose Primary Color",
                hex = "#054605",
                onHexChange = {},
                color = parseHexColor("#054605"),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}