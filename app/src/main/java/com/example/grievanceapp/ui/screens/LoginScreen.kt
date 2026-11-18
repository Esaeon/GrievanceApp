package com.example.grievanceapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grievanceapp.ui.modules.LoginButton
import com.example.grievanceapp.ui.modules.PromptText
import com.example.grievanceapp.ui.modules.TextEntryBox
import com.example.grievanceapp.ui.theme.GrievanceAppTheme

// onLogin returns true when the login worked, false when it was rejected
@Composable
fun LoginScreen(
    onLogin: (username: String, password: String) -> Boolean,
    modifier: Modifier = Modifier
) {
    var username by rememberSaveable { mutableStateOf("") }
    // remember instead of rememberSaveable, so the password is never written
    // into the saved instance state
    var password by remember { mutableStateOf("") }
    var failed by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .safeDrawingPadding()
                .padding(32.dp)
        ) {
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(32.dp))

            PromptText(text = "Username:", modifier = Modifier.align(Alignment.Start))
            Spacer(Modifier.height(8.dp))
            TextEntryBox(
                value = username,
                onValueChange = { username = it; failed = false },
                placeholder = "Username",
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            PromptText(text = "Password:", modifier = Modifier.align(Alignment.Start))
            Spacer(Modifier.height(8.dp))
            TextEntryBox(
                value = password,
                onValueChange = { password = it; failed = false },
                placeholder = "Password",
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            if (failed) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Incorrect username or password.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(24.dp))
            LoginButton(
                onClick = { failed = !onLogin(username.trim(), password) },
                modifier = Modifier.fillMaxWidth()
            )
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
fun LoginScreenPreview() {
    GrievanceAppTheme {
        LoginScreen(onLogin = { _, _ -> false })
    }
}