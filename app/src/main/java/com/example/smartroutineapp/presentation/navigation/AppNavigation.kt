package com.example.smartroutineapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.smartroutineapp.presentation.screens.main.MainScreen
import com.example.smartroutineapp.presentation.screens.task.TaskScreen

@Composable
fun MainNavGraph(
    appNavController: NavHostController = rememberNavController(),
    startDestination: NavigableGraph = NavigableGraph.Main,
) {
    NavHost(
        navController = appNavController,
        startDestination = startDestination
    ) {
        composable<NavigableGraph.Main> {
            MainScreen(appNavController)
        }
        composable<NavigableGraph.Task> { backStackEntry ->
            val route: NavigableGraph.Task = backStackEntry.toRoute()
            TaskScreen(
                navigateBack = { appNavController.popBackStack() },
                title = route.name
            )
        }
    }
}
