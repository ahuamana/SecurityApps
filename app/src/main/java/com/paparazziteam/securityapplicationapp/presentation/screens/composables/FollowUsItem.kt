package com.paparazziteam.securityapplicationapp.presentation.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paparazziteam.securityapplicationapp.R

@Composable
fun FollowUsItem (
    onClickYoutube: () -> Unit
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFfd3832)
        ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Visitanos en: ",
                color = Color.White,
                fontSize = 12.sp,
                letterSpacing = 0.sp,
            )
            Card(
                shape = CircleShape,
                modifier = Modifier.size(25.dp)) {
                Image(
                    modifier = Modifier.clickable(onClick = { onClickYoutube() }),
                    painter = painterResource(id = R.drawable.ic_youtube), contentDescription = "Logo Youtube")
            }

        }
    }
}

@Composable
fun FollowUsItemPreview(){
    FollowUsItem(onClickYoutube = {})
}