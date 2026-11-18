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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.modules.CancelButton
import com.example.grievanceapp.ui.modules.PromptText
import com.example.grievanceapp.ui.modules.SubmitButton
import com.example.grievanceapp.ui.modules.TextEntryBox
import com.example.grievanceapp.ui.theme.GrievanceAppTheme

@Composable
fun FormScreen(
    prompt: String,
    onCancel: () -> Unit,
    onSubmit: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Input Here..."
) {
    var text by rememberSaveable { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .padding(20.dp)
        ) {
            PromptText(text = prompt)
            Spacer(Modifier.height(24.dp))
            TextEntryBox(
                value = text,
                onValueChange = { text = it },
                placeholder = placeholder,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CancelButton(onClick = onCancel, modifier = Modifier.weight(1f))
                SubmitButton(onClick = { onSubmit(text) }, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ComplaintScreen(
    onCancel: () -> Unit,
    onSubmit: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FormScreen(
        prompt = "Write your complaint here:",
        onCancel = onCancel,
        onSubmit = onSubmit,
        modifier = modifier
    )
}

@Composable
fun SuggestionScreen(
    onCancel: () -> Unit,
    onSubmit: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FormScreen(
        prompt = "Write your suggestion here:",
        onCancel = onCancel,
        onSubmit = onSubmit,
        modifier = modifier
    )
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ComplaintScreenPreview() {
    GrievanceAppTheme {
        ComplaintScreen(onCancel = {}, onSubmit = {})
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SuggestionScreenPreview() {
    GrievanceAppTheme {
        SuggestionScreen(onCancel = {}, onSubmit = {})
    }
}