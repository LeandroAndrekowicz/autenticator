package com.nexkey.auth.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast

import com.nexkey.auth.components.AuthCodeCard
import com.nexkey.auth.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var showScanner by remember { mutableStateOf(false) }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            showScanner = true
            Toast.makeText(context, "Permissão concedida! Abrindo Scanner...", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permissão da Câmera negada.", Toast.LENGTH_SHORT).show()
        }
    }

    if (showScanner) {
        ScannerScreen(
            onBack = {
                showScanner = false
            }
        )
    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                AuthCodeCard(serviceName = "GitHub", code = "123 456")
                AuthCodeCard(serviceName = "Google", code = "789 012")
                AuthCodeCard(serviceName = "Facebook", code = "345 678")
                Spacer(modifier = Modifier.height(100.dp))
            }

            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Adicionar novo código",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 32.dp, bottom = 32.dp)
                    .size(50.dp)
                    .clickable {
                        cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                    }
            )
        }
    }
}