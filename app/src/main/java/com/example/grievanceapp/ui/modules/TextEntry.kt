package com.example.grievanceapp.ui.modules

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.VisualTransformation
import com.example.grievanceapp.ui.theme.GrievanceAppTheme

@Composable
fun TextEntryBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Sample Text...",
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val scheme = MaterialTheme.colorScheme

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = scheme.secondaryContainer,
            unfocusedContainerColor = scheme.secondaryContainer,
            disabledContainerColor = scheme.secondaryContainer,
            focusedTextColor = scheme.onSecondaryContainer,
            unfocusedTextColor = scheme.onSecondaryContainer,
            focusedPlaceholderColor = scheme.outline,
            unfocusedPlaceholderColor = scheme.outline,
            cursorColor = scheme.onSecondaryContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TextEntryBoxPreview() {
    GrievanceAppTheme {
        Surface {
            var text by remember { mutableStateOf("") }
            TextEntryBox(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}