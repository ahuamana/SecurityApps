package com.paparazziteam.securityapplicationapp.presentation.screens.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.paparazziteam.securityapplicationapp.data.fake.FakeFridaItems
import com.paparazziteam.securityapplicationapp.domain.EncryptionViewIntent
import com.paparazziteam.securityapplicationapp.presentation.screens.navigation.BottomNavItem
import com.paparazziteam.securityapplicationapp.presentation.screens.navigation.addNestedGraphMenu
import com.paparazziteam.securityapplicationapp.presentation.screens.screens.encription.AESEncryptionParent
import com.paparazziteam.securityapplicationapp.presentation.screens.viewmodels.EncryptionScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val controller:NavHostController = rememberNavController()
    val itemsMenu = listOf(
        BottomNavItem.Home,
        BottomNavItem.Encryption
    )

    Scaffold(
        bottomBar = {
            BottomBarCustom(navController = controller, items = itemsMenu)
        }
    ) { contentPadding ->

        //use a composable
        addNestedGraphMenu(navController = controller)
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