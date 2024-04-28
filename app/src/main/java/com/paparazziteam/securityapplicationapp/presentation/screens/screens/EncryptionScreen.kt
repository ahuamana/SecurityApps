package com.paparazziteam.securityapplicationapp.presentation.screens.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.paparazziteam.securityapplicationapp.R

@Composable
fun EncryptionScreen(modifier: Modifier = Modifier,
                     onClickEncrypt: (String) -> Unit,
                     onClickDecrypt: (String) -> Unit
) {

    val backgroundLottie by  rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.dot_pattern_background))

    Box {
        LottieAnimation(composition = backgroundLottie, iterations = LottieConstants.IterateForever)
        ContentEncryptionScreen(modifier = modifier, onClickEncrypt = onClickEncrypt, onClickDecrypt = onClickDecrypt)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContentEncryptionScreen(
    modifier: Modifier,
    onClickEncrypt: (String) -> Unit,
    onClickDecrypt: (String) -> Unit

){

    var inputTextEncrypt by remember { mutableStateOf("") }
    var outputTextEncrypt by remember { mutableStateOf("") }

    var inputTextDecrypt by remember { mutableStateOf("") }
    var outputTextDecrypt by remember { mutableStateOf("") }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        Text(text = "Desafio de Encriptación",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        )

        //In Spanish
        Text(text = "En esta sección se mostrará un desafio de encriptación, donde deberás de resolverlo para poder avanzar al siguiente nivel.",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp
            )
        )

        //Input Text to Encrypt and output text
        Spacer(modifier = Modifier.size(16.dp))


        TextField(
            value = inputTextEncrypt,
            onValueChange = { inputTextEncrypt = it },
            label = { Text("Texto a encriptar") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.size(16.dp))


        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            Button(onClick = { onClickEncrypt(inputTextEncrypt)}) {
                Text(text = "Encriptar")
            }
        }

        TextField(value = outputTextEncrypt,
            onValueChange = { outputTextEncrypt = it },
            label = { Text("Texto encriptado") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            //avoid focus
            readOnly = true
        )

        //Texto para desencriptar

        Spacer(modifier = Modifier.size(40.dp))

        //Add line as separator

        Divider(modifier = Modifier.fillMaxWidth())


        TextField(
            value = inputTextDecrypt,
            onValueChange = { inputTextDecrypt = it },
            label = { Text("Texto a desencriptar") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.size(16.dp))

        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            Button(onClick = { onClickDecrypt(inputTextDecrypt) }) {
                Text(text = "Desencriptar")
            }
        }

        TextField(value = outputTextDecrypt,
            onValueChange = { outputTextDecrypt = it },
            label = { Text("Texto desencriptado") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            //avoid focus
            readOnly = true
        )

    }
}


@Preview
@Composable
private fun EncryptionScreenPrev() {
    EncryptionScreen(
        onClickEncrypt = {},
        onClickDecrypt = {}
    )
}