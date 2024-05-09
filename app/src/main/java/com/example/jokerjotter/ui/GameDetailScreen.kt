package com.example.jokerjotter.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jokerjotter.JokerJotterTopAppBar
import com.example.jokerjotter.R
import com.example.jokerjotter.ui.navigation.BottomNavigationItem
import com.example.jokerjotter.ui.navigation.NavigationDestination
import com.example.jokerjotter.ui.theme.AppTheme
import com.example.jokerjotter.ui.theme.onSurfaceVariantLight
import com.example.jokerjotter.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.jokerjotter.ui.theme.outlineVariantLight
import com.example.jokerjotter.ui.theme.scrimLight

object GameDetailDestination : NavigationDestination {
    override val route = "game_detail"
    override val titleRes = R.string.game_detail
}

var players = listOf("Niki", "Pilili", "Gatalina", "Apuki", "Piropo", "Kathy")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    navController: NavHostController = rememberNavController(),
    editDetails: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val background = painterResource(R.drawable.backgroundapp)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        topBar = {
            JokerJotterTopAppBar(
                title = stringResource(R.string.game_detail),
                canNavigateBack = false,
                canSave = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = editDetails,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_item_title)
                )
            }
        },
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = background,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Juego 1",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_small))
                        .fillMaxWidth()
                )
                HorizontalDivider(
                    thickness = dimensionResource(R.dimen.thickness_divider),
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
                )
                Column(
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
                ) {
                    CardPointsSection()
                    PlayerCardSection()
                }
            }
        }
    }

}

@Composable
fun CardPointsSection() {
    Text(
        text = stringResource(R.string.score_cards),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSecondary
    )
    HorizontalDivider(
        thickness = dimensionResource(R.dimen.thickness_divider),
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        ) {
            CardPoints(
                image = painterResource(R.drawable.as_card),
                description = stringResource(R.string.as_card),
                points = "20"
            )
            CardPoints(
                image = painterResource(R.drawable.one_eyed),
                description = stringResource(R.string.one_eyed),
                points = "15"
            )
        }
        CardPoints(
            image = painterResource(R.drawable.jokers),
            description = stringResource(R.string.joker),
            points = "30"
        )
    }
}

@Composable
fun CardPoints(image: Painter, description: String, points: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small)
        )
    ) {
        Image(
            painter = image,
            contentDescription = description,
            modifier = Modifier.size(dimensionResource(R.dimen.image_small))
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_small)))
        Text(
            text = "=",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_small)))
        Text(
            text = points,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun PlayerCardSection() {
    Text(
        text = stringResource(R.string.players),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSecondary
    )
    HorizontalDivider(
        thickness = dimensionResource(R.dimen.thickness_divider),
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
    )
    LazyColumn {
        items(items = players, key = { players.indexOf(it) }) { player ->
            PlayerCard(player = player)
        }
    }
}

@Composable
fun PlayerCard(player: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .padding(dimensionResource(R.dimen.padding_small))
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.edit_item_title),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = player,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val barItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Setup
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = barItems.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
            containerColor = scrimLight
        ) {
            barItems.forEach { barItem ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor = outlineVariantLight,
                        selectedIconColor = outlineVariantLight,
                        indicatorColor = onSurfaceVariantLight,
                        unselectedIconColor = onSurfaceVariantLightMediumContrast,
                        unselectedTextColor = onSurfaceVariantLightMediumContrast
                    ),
                    label = {
                        Text(
                            text = barItem.label,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = barItem.icon,
                            contentDescription = barItem.label
                        )
                    },
                    selected = barItem.route == currentDestination?.route,
                    onClick = {
                        navController.navigate(barItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameDetailScreenPreview() {
    AppTheme {
        GameDetailScreen()
    }
}