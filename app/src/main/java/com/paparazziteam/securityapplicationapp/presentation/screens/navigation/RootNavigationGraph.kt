package com.paparazziteam.securityapplicationapp.presentation.screens.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.paparazziteam.securityapplicationapp.R
import com.paparazziteam.securityapplicationapp.data.fake.FakeFridaItems
import com.paparazziteam.securityapplicationapp.domain.EncryptionViewIntent
import com.paparazziteam.securityapplicationapp.presentation.screens.screens.DashboardScreen
import com.paparazziteam.securityapplicationapp.presentation.screens.screens.EncryptionScreen
import com.paparazziteam.securityapplicationapp.presentation.screens.screens.HomeSp
import com.paparazziteam.securityapplicationapp.presentation.screens.screens.encription.AESEncryptionParent
import com.paparazziteam.securityapplicationapp.presentation.screens.screens.encription.EncryptedSharedPreferencesScreen
import com.paparazziteam.securityapplicationapp.presentation.screens.viewmodels.EncryptedSharedPreferencesViewModel
import com.paparazziteam.securityapplicationapp.presentation.screens.viewmodels.EncryptionScreenViewModel


@Composable
fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        composable(route = Graph.HOME) {
            DashboardScreen()
        }
    }
}

@Composable
fun addNestedGraphMenu(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeSp(contentPadding = PaddingValues(0.dp))
        }
        composable(BottomNavItem.Encryption.route) {
            val backgroundLottie by  rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.dot_pattern_background))
            Box(modifier = Modifier.fillMaxSize()) {
                LottieAnimation(composition = backgroundLottie, iterations = LottieConstants.IterateForever)
                AESEncryptionParent(
                    modifier = Modifier,
                    onClickCard = { item ->
                        navController.navigate(route = item.route.value) },
                    listItemFrida = FakeFridaItems.getFridaItems())
            }

        }

        addNestedGraphEncrypted(navController = navController)

    }
}

//EncryptedModes
fun NavGraphBuilder.addNestedGraphEncrypted(navController: NavHostController) {
    navigation(
        route = Graph.FRIDA,
        startDestination = EncryptionScreen.AESEncryption.value
    ) {
        composable(EncryptionScreen.AESEncryption.value) {
            val viewModel = hiltViewModel<EncryptionScreenViewModel>()
            val states by viewModel.intent.collectAsStateWithLifecycle()

            EncryptionScreen(
                textEncrypt = states.inputTextEncrypt,
                textDecrypt = states.inputTextDecrypt,
                textOutputEncrypt = states.outputTextEncrypt,
                textOutputDecrypt = states.outputTextDecrypt,
                modifier = Modifier,
                onClickEncrypt = { text -> viewModel.encryptText(text) },
                onClickDecrypt = { text -> viewModel.decryptText(text) },
                onTextChangeEncrypt = { text -> viewModel.processIntent(EncryptionViewIntent.TextChangedEncrypt(text)) },
                onTextChangeDecrypt = { text ->
                    viewModel.processIntent(EncryptionViewIntent.TextChangedDecrypt(text))
                }
            )
        }
        composable(EncryptionScreen.OtherEncryption.value) {
            //TODO: Add other encryption
            Text(text = "Other Encryption")
        }

        composable(EncryptionScreen.EncryptedSharedPreferences.value) {

            val viewModel = hiltViewModel<EncryptedSharedPreferencesViewModel>()
            val states by viewModel.intent.collectAsStateWithLifecycle()
            val context = LocalContext.current

            EncryptedSharedPreferencesScreen(
                modifier = Modifier,
                onClickEncrypt = { text ->
                    viewModel.encryptedInEncryptedSharedPreferences(text)
                },
                onTextChangeEncrypt = { text ->
                    viewModel.processIntent(EncryptionViewIntent.TextChangedEncrypt(text))
                },
                textEncrypt = states.inputTextEncrypt,
                onClickDecrypt = {
                    val text = viewModel.getEncryptedTextFromEncryptedSharedPreferences()
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                },
            )
        }
    }
}




object Graph {
    const val ROOT = "root"
    const val HOME = "home_graph"
    const val DASHBOARD = "dashboard_graph"
    const val FRIDA = "frida_graph"
    const val DETAILS = "details_graph"
}

sealed class BottomNavItem(val route: String, val title: String, val icon: Int) {
    data object Home : BottomNavItem("home", "Home", icon = R.drawable.ic_home)
    data object Encryption : BottomNavItem("encryption", "Encryption", R.drawable.ic_air)
    data object EncryptionDetails : BottomNavItem("encryption_details", "Encryption Details", R.drawable.ic_air)
    data object Frida : BottomNavItem("frida", "Frida", com.paparazziteam.securityapplicationapp.R.drawable.ic_home)
}

sealed class EncryptionScreen(val value: String) {
    data object AESEncryption : EncryptionScreen("aes")
    data object EncryptedSharedPreferences : EncryptionScreen("encrypted_shared_preferences")
    data object OtherEncryption : EncryptionScreen("other")
}
