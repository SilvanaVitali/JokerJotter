package com.example.jokerjotter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokerjotter.R
import com.example.jokerjotter.ui.navigation.NavigationDestination
import com.example.jokerjotter.ui.theme.AppTheme

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen(
    navigateToGameEntry: () -> Unit,
    navigateToPreviousGames: () -> Unit
) {
    val background = painterResource(R.drawable.jokerjotterbackground)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = background,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
            )
        HomeBody(
            navigateToGameEntry = navigateToGameEntry,
            navigateToPreviousGames = navigateToPreviousGames
            )
    }
}

@Composable
fun HomeBody(
    navigateToGameEntry: () -> Unit,
    navigateToPreviousGames: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeButtons(
            onClick = navigateToGameEntry,
            text = stringResource(R.string.new_game)
        )
        HomeButtons(
            onClick = navigateToPreviousGames,
            text = stringResource(R.string.previous_games)
        )
    }
}

@Composable
fun HomeButtons(
    onClick: () -> Unit,
    text: String
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .padding(bottom = dimensionResource(R.dimen.padding_extra_large))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
        AppTheme {
            HomeScreen(
                navigateToGameEntry = {},
                navigateToPreviousGames = {}
            )
        }
}