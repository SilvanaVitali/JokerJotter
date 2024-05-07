package com.example.jokerjotter

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jokerjotter.ui.navigation.JokerJotterNavHost

@Composable
fun JokerJotterApp(navController: NavHostController = rememberNavController()) {
    JokerJotterNavHost(navController = navController)
}