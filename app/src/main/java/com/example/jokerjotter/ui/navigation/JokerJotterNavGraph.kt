package com.example.jokerjotter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jokerjotter.ui.HomeDestination
import com.example.jokerjotter.ui.HomeScreen

@Composable
fun JokerJotterNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToGameEntry = {},
                navigateToPreviousGames = {}
            )
        }
    }
}