package com.paparazziteam.securityapplicationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.paparazziteam.securityapplicationapp.presentation.screens.HomeScreen
import com.paparazziteam.securityapplicationapp.presentation.screens.HomeSp
import com.paparazziteam.securityapplicationapp.presentation.screens.HomeViewModel
import com.paparazziteam.securityapplicationapp.ui.theme.SecurityApplicationAppTheme
import com.paparazziteam.securityapplicationapp.usecases.PokemonState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecurityApplicationAppTheme {
                HomeSp()
            }
        }
    }
}