package com.paparazziteam.securityapplicationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.paparazziteam.securityapplicationapp.presentation.screens.navigation.RootNavigationGraph
import com.paparazziteam.securityapplicationapp.presentation.screens.screens.HomeSp
import com.paparazziteam.securityapplicationapp.ui.theme.SecurityApplicationAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecurityApplicationAppTheme {
                RootNavigationGraph()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TestSafetyNet() {
    HomeSp()
}