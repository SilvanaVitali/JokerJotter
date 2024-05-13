package com.example.jokerjotter.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jokerjotter.R
import com.example.jokerjotter.ui.navigation.BottomNavigationItem
import com.example.jokerjotter.ui.navigation.GameNavGraph
import com.example.jokerjotter.ui.navigation.NavigationDestination
import com.example.jokerjotter.ui.theme.AppTheme

object GameScreenDestination : NavigationDestination {
    override val route = "game_screen"
    override val titleRes = R.string.game_screen
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameScreen(
    navController: NavHostController = rememberNavController(),
    startDestination: String = BottomNavigationItem.Setup.route
) {
        GameNavGraph(navController = navController, startDestination = startDestination)
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    AppTheme {
        GameScreen()
    }
}