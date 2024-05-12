package com.paparazziteam.securityapplicationapp.presentation.screens.composables


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.paparazziteam.securityapplicationapp.R
import timber.log.Timber
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AESEncryptionCard(
    modifier: Modifier = Modifier,
    label: String,
    icon: Int,
    customColor : Color = Color.Unspecified,
    onClickCard: () -> Unit,
    ) {

    val animationStart by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.anim_gradient))

    //Create a card with image in the middle with a rounded corner and ramdom color
    val darkColors = listOf(
        Color(0xFF232F34), // Dark Slate Gray
        Color(0xFF4A6572), // Light Slate Gray
        Color(0xFFC62828), // Dark Red
        Color(0xFFAD1457), // Dark Pink
        Color(0xFFE27D60), // Tuscan Red
        Color(0xFF85DCB0),  // Summer Sky
        Color(0xFFE8A87C), // Coral
        Color(0xFFC38D9E), // Eucalyptus
        Color(0xFF41B3A3)  // Ocean Green
    )

    val randomColor = darkColors.random()
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            //vertical card
            .defaultMinSize(minHeight = 200.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false),
                onClick = onClickCard
            ), colors = CardDefaults.cardColors(
            containerColor = if(customColor == Color.Unspecified) randomColor else customColor,
            contentColor = Color.White
        ),

        ) {

        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                LottieAnimation(
                    //center the animation
                    modifier = Modifier.size(0.dp),
                    composition = animationStart,
                    iterations = LottieConstants.IterateForever)
            }


        }

    }

}







    @Preview
@Composable
private fun AESEncryptionCardPreview() {
    AESEncryptionCard(
        label = "AES Encryption",
        icon = R.drawable.ic_key,
        customColor = Color(0xFF85DCB0)
    ) {
        Timber.d("Card clicked")
    }
}