package com.example.jokerjotter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jokerjotter.ui.GameDetailDestination
import com.example.jokerjotter.ui.GameEditDestination
import com.example.jokerjotter.ui.GameEditScreen
import com.example.jokerjotter.ui.GameRoundsDestination
import com.example.jokerjotter.ui.GameScreen
import com.example.jokerjotter.ui.HomeDestination
import com.example.jokerjotter.ui.HomeScreen
import com.example.jokerjotter.ui.RoundDestination
import com.example.jokerjotter.ui.RoundScreen

@Composable
fun JokerJotterNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToGameEntry = { navController.navigate(GameEditDestination.route) },
                navigateToPreviousGames = {}
            )
        }
        composable(route = GameEditDestination.route) {
            GameEditScreen(
                navigateBack = { navController.navigate(HomeDestination.route) },
                saveDetails = { navController.navigate(GameDetailDestination.route) }
            )
        }
        composable(route = GameDetailDestination.route) {
            GameScreen(startDestination = BottomNavigationItem.Setup.route)
        }
        composable(route = RoundDestination.route) {
            RoundScreen(
                navigateBack = { navController.navigate(GameRoundsDestination.route) }
            )
        }
        composable(route = GameRoundsDestination.route) {
            GameScreen(startDestination = BottomNavigationItem.Rounds.route)
        }
    }
}