package com.paparazziteam.securityapplicationapp.presentation.screens


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.paparazziteam.securityapplicationapp.R
import com.paparazziteam.securityapplicationapp.presentation.screens.ads.BannerAd
import com.paparazziteam.securityapplicationapp.ui.theme.Green40
import com.paparazziteam.securityapplicationapp.ui.theme.SecurityApplicationAppTheme
import com.paparazziteam.securityapplicationapp.usecases.PokemonState
import timber.log.Timber

@Composable
fun HomeScreen(
    https: HomeViewModel.VisibleStateWith,
    httpsPlusSSL: HomeViewModel.VisibleStateWith,
    statePokemon : PokemonState,
    onClickRetrofitHilt: () -> Unit,
    onClickRetrofitHiltSsl: () -> Unit,
    onClickYoutube: () -> Unit,
) {
    val backgroundLottie by  rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.dot_pattern_background))
    val lottieWorking by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.bottom_working_lottie))

    LaunchedEffect(key1 = statePokemon){
        Timber.d("HomeScreen: %s", statePokemon)
        when(statePokemon){
            is PokemonState.Error -> {
                Timber.d("HomeScreen: Error ${statePokemon.error.message}")
            }
            PokemonState.HideLoading -> {
                //Timber.d("HomeScreen: HideLoading")
            }
            PokemonState.Idle -> {
                //Timber.d("HomeScreen: Idle")
            }
            PokemonState.ShowLoading -> {
                //Timber.d("HomeScreen: ShowLoading")
            }
            is PokemonState.Success -> {
                val pokemonData = statePokemon.data
            }
        }
    }



    Box(modifier = Modifier.fillMaxSize()) {
        //background
        LottieAnimation(composition = backgroundLottie, iterations = LottieConstants.IterateForever)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
            ) {
                /*BannerAd(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp))*/
            }

            Spacer(modifier = Modifier.size(20.dp))


            //portada_paparazziteam

            Card(
                shape = androidx.compose.foundation.shape.CircleShape,
                modifier = Modifier.size(200.dp)) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Portada PaparazziTeam")
            }


            Spacer(modifier = Modifier.size(20.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                textAlign = TextAlign.Justify,
                fontSize = 12.sp,
                letterSpacing = 0.sp,
                text = stringResource(R.string.description_home_title)
            )

            Spacer(modifier = Modifier.size(20.dp))


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onClick = { onClickRetrofitHilt() }) {

                AnimatedVisibility(visible = https.isVisible) {
                    Row() {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = https.icon?:R.drawable.ic_blank),
                            contentDescription = "Retrofit + Hilt",
                            //colorFilter = ColorFilter.tint(Green40)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
                Text(text = "HTTPS (RETROFIT + HILT)")
            }

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onClick = { onClickRetrofitHiltSsl() }) {

                AnimatedVisibility(visible = httpsPlusSSL.isVisible) {
                    Row() {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = httpsPlusSSL.icon?:R.drawable.ic_blank),
                            contentDescription = "SSL Pinning",
                            //colorFilter = ColorFilter.tint(Green40)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
                Text(text = "SSL PINNING (RETROFIT + HILT)")
            }

            Spacer(modifier = Modifier.size(20.dp))
            //Lottie animation bottom

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LottieAnimation(
                    modifier = Modifier.size(200.dp),
                    composition = lottieWorking,
                    iterations = LottieConstants.IterateForever
                )
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
                            shape = androidx.compose.foundation.shape.CircleShape,
                            modifier = Modifier.size(25.dp)) {
                            Image(
                                modifier = Modifier.clickable(onClick = { onClickYoutube() }),
                                painter = painterResource(id = R.drawable.ic_youtube), contentDescription = "Logo Youtube")
                        }

                    }
                }
            }
        }
    }

}


@Composable
fun HomeSp() {
    val viewModel: HomeViewModel = hiltViewModel()
    val statePokemon by viewModel.statePokemon.collectAsStateWithLifecycle()

    val isVisibleHttps by viewModel.isVisibleBypassHttpsState.collectAsStateWithLifecycle()
    val isVisibleHttpsPlusSSL by viewModel.isVisibleBypassCertificatePinningState.collectAsStateWithLifecycle()

    val context = LocalUriHandler.current
    val localContext = LocalContext.current

    HomeScreen(
        isVisibleHttps,
        isVisibleHttpsPlusSSL,
        statePokemon = statePokemon,
        onClickRetrofitHilt = { viewModel.getPokemonInfo("ditto") },
        onClickYoutube = {
            context.openUri(localContext.getString(R.string.youtube_channel))
        },
        onClickRetrofitHiltSsl = { viewModel.getPokemonInfoSsl("ditto") }
    )
}

@Preview
@Composable
fun HomeScreenPrev() {
    SecurityApplicationAppTheme() {
        HomeScreen(
            https = HomeViewModel.VisibleStateWith(true, R.drawable.ic_error),
            httpsPlusSSL = HomeViewModel.VisibleStateWith(true, R.drawable.ic_check_circle),
            statePokemon = PokemonState.Idle,
            onClickRetrofitHilt = {
                val pokemon = "ditto"
                //viewModel.getPokemonInfo(pokemon)
            },
            onClickYoutube = {

            },
            onClickRetrofitHiltSsl = {

            }
        )
    }

}

