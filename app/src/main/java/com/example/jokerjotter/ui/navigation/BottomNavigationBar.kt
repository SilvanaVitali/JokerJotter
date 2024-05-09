package com.example.jokerjotter.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jokerjotter.ui.GameDetailDestination
import com.example.jokerjotter.ui.HomeDestination

sealed class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = "",
) {
    object Home : BottomNavigationItem(
        label = "Home",
        icon = Icons.Filled.Home,
        route = HomeDestination.route
    )

    object Setup : BottomNavigationItem(
        label = "Setup",
        icon = Icons.Filled.Settings,
        route = GameDetailDestination.route
    )
}