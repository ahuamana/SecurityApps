package com.paparazziteam.securityapplicationapp.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paparazziteam.securityapplicationapp.R
import com.paparazziteam.securityapplicationapp.presentation.screens.screens.DashboardScreen


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

        composable(route = Graph.DETAILS){

        }

        composable(route = Graph.FRIDA){

        }

    }
    
}

object Graph {
    const val ROOT = "root"
    const val HOME = "home"
    const val FRIDA = "frida"
    const val DETAILS = "details"
}

sealed class BottomNavItem(val route: String, val title: String, val icon: Int) {
    object Home : BottomNavItem("home", "Home", icon = R.drawable.ic_home)
    object Encryption : BottomNavItem("encryption", "Encryption", R.drawable.ic_air)
    object Frida : BottomNavItem("frida", "Frida", com.paparazziteam.securityapplicationapp.R.drawable.ic_home)
}
