package com.nexkey.auth.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexkey.auth.components.CameraScanner
import com.nexkey.auth.R

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(onBack: () -> Unit) {
    val blackBackground = Color.Black
    var scannedQrCode by remember { mutableStateOf<String?>(null) }

    Scaffold(
        containerColor = blackBackground,

        topBar = {
            TopAppBar(
                title = {
                    "Escanear QR Code"
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = blackBackground,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                }
            )
        },

        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (scannedQrCode == null) {
                CameraScanner(
                    onBarcodeScanned = { value ->
                        scannedQrCode = value
                    }
                )

                Image(
                    painter = painterResource(id = R.drawable.qrcode),
                    contentDescription = "Área de Foco do Scanner",
                    modifier = Modifier.size(300.dp).align(Alignment.Center)
                )

            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "QR Code Lido com Sucesso!",
                        color = Color.Green,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = scannedQrCode!!,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}