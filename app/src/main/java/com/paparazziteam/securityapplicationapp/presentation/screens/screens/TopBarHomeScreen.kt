package com.paparazziteam.securityapplicationapp.presentation.screens.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paparazziteam.securityapplicationapp.R

@Composable
fun TopBarHomeScreen(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card(
            shape = androidx.compose.foundation.shape.CircleShape,
            modifier = Modifier.size(90.dp)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Portada PaparazziTeam")
        }

        Spacer(modifier = Modifier.size(10.dp))

        Text(text = "Pentesting School",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp
            )
        )

    }

}


@Preview
@Composable
private fun TopBarHomeScreenPrev() {
    TopBarHomeScreen()
}