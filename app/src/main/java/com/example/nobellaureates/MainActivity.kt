package com.example.nobellaureates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.nobellaureates.presentation.common.TokenManager
import com.example.nobellaureates.presentation.navigation.NavGraph
import com.example.nobellaureates.presentation.theme.NobellaureatesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NobellaureatesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val tokenManager = remember { TokenManager(applicationContext) }
                    NavGraph(
                        startDestination = if (tokenManager.isLoggedIn()) "list" else "login"
                    )
                }
            }
        }
    }
}
