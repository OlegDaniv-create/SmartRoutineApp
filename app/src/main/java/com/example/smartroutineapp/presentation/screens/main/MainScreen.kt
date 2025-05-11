package com.example.smartroutineapp.presentation.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smartroutineapp.presentation.navigation.BottomNavigationBar
import com.example.smartroutineapp.presentation.navigation.NavigableGraph.BottomNavItem
import com.example.smartroutineapp.presentation.screens.home.HomeScreen
import com.example.smartroutineapp.presentation.screens.list.ListScreen
import com.example.smartroutineapp.presentation.screens.user.UserScreen
import com.example.smartroutineapp.presentation.view.TitleToolBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    appNavController: NavHostController,
) {
    val bottomNavController = rememberNavController()
    val currentBackStack by bottomNavController.currentBackStackEntryAsState()
    val currentScreen = BottomNavItem.toList.find {
        currentBackStack?.destination?.hasRoute(it::class) == true
    } ?: BottomNavItem.Home
    Scaffold(
        topBar = {
            TitleToolBar(title = currentScreen.toString())
        },
        bottomBar = {
            BottomNavigationBar(
                currentScreen = currentScreen,
                onItemSelected = { item ->
                    bottomNavController.navigate(item) {
                        popUpTo(bottomNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        content = { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = bottomNavController,
                startDestination = BottomNavItem.Home,
            ) {
                composable<BottomNavItem.Home> {
                    HomeScreen(navigate = appNavController)
                }

                composable<BottomNavItem.List> {
                    ListScreen()
                }

                composable<BottomNavItem.User> {
                    UserScreen()
                }
            }
        }
    )
}
