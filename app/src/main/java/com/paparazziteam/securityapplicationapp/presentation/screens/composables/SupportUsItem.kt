package com.paparazziteam.securityapplicationapp.presentation.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paparazziteam.securityapplicationapp.presentation.screens.BannerScreen

@Composable
fun SupportUsItem() {
    Card {
        Column() {
            Text(text = "Apoyanos", modifier = Modifier.padding(10.dp))
            Text(text = "Con tu apoyo podemos seguir creando contenido", modifier = Modifier.padding(10.dp))
            //Click here to see adds
            Text(
                style = androidx.compose.ui.text.TextStyle(color = androidx.compose.ui.graphics.Color.Blue),
                text = "Click aqui para ver anuncios", modifier = Modifier.padding(10.dp)
            )

            BannerScreen()

        }
    }
}


@Preview
@Composable
fun SupportUsItemPreview() {
    SupportUsItem()
}