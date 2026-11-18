package com.example.grievanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.grievanceapp.ui.screens.HomeScreen
import com.example.grievanceapp.ui.theme.GrievanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GrievanceAppTheme {
                HomeScreen(
                    onFileComplaint = {},
                    onMakeSuggestion = {},
                    onContacts = {},
                    onHelp = {},
                    onThemeSelection = {},
                    onLogOut = {}
                )
            }
        }
    }
}