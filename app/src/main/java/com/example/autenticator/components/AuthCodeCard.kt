package com.example.autenticator.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalContext
import com.example.autenticator.R


fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    val clip = ClipData.newPlainText("Código de Autenticação", text)

    clipboard.setPrimaryClip(clip)

    Toast.makeText(context, "Código copiado!", Toast.LENGTH_SHORT).show()
}

@Composable
fun AuthCodeCard(serviceName: String, code: String) {
    val context = LocalContext.current
    val darkGrayBackground = Color(0xFF1A1A1A)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = darkGrayBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Usuário: $serviceName",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = code,
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 2.sp
                )

                Row(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.copy),
                        contentDescription = "Copiar Código",
                        modifier = Modifier
                            .clickable() {
                                copyToClipboard(context = context, text = code)
                            }
                            .size(40.dp)
                            .padding(end = 12.dp),
                    )

                    Image(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "Excluir Código",
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
        }
    }
}