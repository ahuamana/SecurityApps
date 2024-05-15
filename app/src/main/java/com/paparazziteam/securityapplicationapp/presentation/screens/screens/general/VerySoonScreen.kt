package com.paparazziteam.securityapplicationapp.presentation.screens.screens.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.paparazziteam.securityapplicationapp.R

@Composable
fun VerySoonScreen(modifier: Modifier = Modifier) {

    val backgroundLottie by  rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.anim_working_life))

    Column(modifier = modifier.fillMaxSize().padding(30.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column {
                LottieAnimation(composition = backgroundLottie, iterations = LottieConstants.IterateForever, modifier = Modifier.fillMaxWidth().height(200.dp))
                Text("Próximamente", modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }

    }
}


@Preview
@Composable
private fun VerySoonScreenPreview() {
    VerySoonScreen()
}