package com.example.jokerjotter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jokerjotter.JokerJotterApp
import com.example.jokerjotter.ui.GameDetailScreen
import com.example.jokerjotter.ui.GameEditDestination
import com.example.jokerjotter.ui.GameRoundsScreen
import com.example.jokerjotter.ui.GameScreenDestination
import com.example.jokerjotter.ui.HomeDestination
import com.example.jokerjotter.ui.RoundDestination

@Composable
fun GameNavGraph(
    navController: NavHostController,
    startDestination: String,
    ) {
    NavHost(
        navController = navController,
        route = GameScreenDestination.route,
        startDestination = startDestination
    ) {
        composable(route = BottomNavigationItem.Home.route) {
            JokerJotterApp(startDestination = HomeDestination.route)
        }
        composable(route = BottomNavigationItem.Setup.route) {
            GameDetailScreen(
                navController = navController,
                editDetails = { navController.navigate(GameEditDestination.route) }
            )
        }
        composable(route = GameEditDestination.route) {
            JokerJotterApp(startDestination = GameEditDestination.route)
        }
        composable(route = BottomNavigationItem.Rounds.route) {
            GameRoundsScreen(
                navController = navController,
                navigateToRound = { navController.navigate(RoundDestination.route) }
            )
        }
        composable(route = RoundDestination.route) {
            JokerJotterApp(startDestination = RoundDestination.route)
        }
    }
}