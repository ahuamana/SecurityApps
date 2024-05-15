package com.paparazziteam.securityapplicationapp.presentation.screens.screens.encription

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.paparazziteam.securityapplicationapp.R

@Composable
fun EncryptedSharedPreferencesScreen(
    modifier: Modifier = Modifier,
    onClickEncrypt: (String) -> Unit,
    onClickDecrypt: () -> Unit,
    onTextChangeEncrypt: (String) -> Unit,
    textEncrypt: String) {

    val backgroundLottie by  rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.dot_pattern_background))

    Box {
        LottieAnimation(composition = backgroundLottie, iterations = LottieConstants.IterateForever)
        ContentEncryptedSharedPreferencesScreen(
            onClickEncrypt = onClickEncrypt,
            onTextChangeEncrypt = onTextChangeEncrypt,
            textEncrypt = textEncrypt,
            onClickDecrypt = onClickDecrypt
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContentEncryptedSharedPreferencesScreen(
    onClickEncrypt: (String) -> Unit,
    onTextChangeEncrypt: (String) -> Unit,
    textEncrypt: String,
    onClickDecrypt: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {

        Text(text = "Desafio de Encriptación con EncryptedSharedPreferences",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "En este desafio deberás encriptar un texto y guardarlo en EncryptedSharedPreferences",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "1. Ingresa un texto en el campo de texto",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "2. Presiona el botón de encriptar",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "3. El texto encriptado se guardará en EncryptedSharedPreferences",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))


        TextField(value = textEncrypt, onValueChange = onTextChangeEncrypt,
            label = { Text("Texto a encriptar") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxSize()
        )

        Spacer(modifier = Modifier.size(16.dp))

        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            Button(onClick = { onClickEncrypt(textEncrypt)}) {
                Text(text = "Guardar", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }


        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
            //show saved text in toast

            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = {
                onClickDecrypt()
            }) {
                Text(text = "Mostrar texto guardado", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
    }
}


@Preview
@Composable
private fun EncryptedSharedPreferencesScreenPreview() {
    EncryptedSharedPreferencesScreen(
        onClickEncrypt = {

        },
        onTextChangeEncrypt = {},
        textEncrypt = "",
        onClickDecrypt = {}
    )
}