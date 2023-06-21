package com.paparazziteam.securityapplicationapp.presentation.screens


import android.net.wifi.hotspot2.pps.HomeSp
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.paparazziteam.securityapplicationapp.R
import com.paparazziteam.securityapplicationapp.ui.theme.Green40
import com.paparazziteam.securityapplicationapp.usecases.PokemonState
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@Composable
fun HomeScreen(
    statePokemon : PokemonState,
    onClickRetrofitHilt: () -> Unit = {}
) {

    var isButtonVisible by remember { mutableStateOf(true) }

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
                isButtonVisible = false
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.size(20.dp))

        //portada_paparazziteam
        Card(shape = androidx.compose.foundation.shape.CircleShape, modifier = Modifier.size(200.dp)) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Portada PaparazziTeam")
        }

        Spacer(modifier = Modifier.size(20.dp))

        AnimatedVisibility(visible = isButtonVisible) {
            Button(
                onClick = { onClickRetrofitHilt() }) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_check_circle),
                    contentDescription = "Retrofit + Hilt",
                    colorFilter = ColorFilter.tint(Green40)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "RETROFIT + HILT")
            }
        }
    }
}


@Composable
fun HomeSp() {
    val viewModel: HomeViewModel = hiltViewModel()
    val statePokemon by viewModel.statePokemon.collectAsStateWithLifecycle()

    HomeScreen(
        statePokemon = statePokemon,
        onClickRetrofitHilt = { viewModel.getPokemonInfo("ditto") }
    )
}

@Preview
@Composable
fun HomeScreenPrev() {
    HomeScreen(
        statePokemon = PokemonState.Idle,
        onClickRetrofitHilt = {
            val pokemon = "ditto"
            //viewModel.getPokemonInfo(pokemon)
        }
    )
}

