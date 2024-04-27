package com.paparazziteam.securityapplicationapp.presentation.screens.screens

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.paparazziteam.securityapplicationapp.presentation.screens.navigation.BottomNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(controller: NavHostController = rememberNavController()) {
    val itemsMenu = listOf(
        BottomNavItem.Home,
        BottomNavItem.Encryption
    )

    Scaffold(
        bottomBar = {
            BottomBarCustom(navController = controller, items = itemsMenu)
        }
    ) { contentPadding ->
        NavHost(navController = controller, startDestination = BottomNavItem.Home.route) {
            composable(BottomNavItem.Home.route) {
                HomeSp(contentPadding = contentPadding)
            }
            composable(BottomNavItem.Encryption.route) {
                EncryptionScreen()
            }
        }
    }
}

@Composable
fun BottomBarCustom(navController: NavHostController, items: List<BottomNavItem>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = items.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
            modifier = Modifier.height(80.dp),
            containerColor = Color.Black.copy(alpha = 0.8f),
        ) {
            items.forEach { screen ->
                //setup the alpha for the selected item
                val isSelectedMenu = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                val backgroundAlpha = if (isSelectedMenu) 1f else 0.6f

                NavigationBarItem(
                    label = {
                        Text(text = screen.title, color = Color.White.copy(alpha = backgroundAlpha)) },
                    icon = { Icon(painterResource(id = screen.icon), contentDescription = screen.title, modifier = Modifier.graphicsLayer(alpha = backgroundAlpha)) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    //selectedContentColor = Color.White,
                    //unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        }
    }
}