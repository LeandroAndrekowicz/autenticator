package com.nexkey.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nexkey.auth.pages.HomePage
import com.nexkey.auth.ui.theme.AutenticatorTheme
import com.nexkey.auth.ui.theme.backgroundColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AutenticatorTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding: PaddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor)
                            .padding(innerPadding),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 32.dp)
                        ) {
                            HomePage(modifier = Modifier.size(200.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AutenticatorTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HomePage()
        }
    }
}