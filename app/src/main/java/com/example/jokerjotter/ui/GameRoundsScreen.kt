package com.example.jokerjotter.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jokerjotter.JokerJotterTopAppBar
import com.example.jokerjotter.R
import com.example.jokerjotter.ui.navigation.NavigationDestination
import com.example.jokerjotter.ui.theme.AppTheme
import com.example.jokerjotter.ui.theme.errorContainerLightMediumContrast

object GameRoundsDestination : NavigationDestination {
    override val route = "game_rounds"
    override val titleRes = R.string.game_rounds
}

class Round(val round: String, val isPlayed: Boolean)

val roundList = listOf(
    Round("2 Tríos", true),
    Round("1 Trío - 1 Escala", false),
    Round("2 Escalas", true),
    Round("3 Tríos", false),
    Round("2 Tríos - 1 Escala", true),
    Round("1 Trío - 2 Escalas", true),
    Round("3 Escalas", true),
    Round("4 Tríos", true),
    Round("6 Pares", true),
    Round("Escala Loca", true),
    Round("Escala Color", true),
    Round("Escala Real", true)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameRoundsScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    navigateToRound: () -> Unit = {}
) {
    val background = painterResource(R.drawable.backgroundapp)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        topBar = {
            JokerJotterTopAppBar(
                title = "Juego 1",
                canNavigateBack = false,
                canSave = false,
                scrollBehavior = scrollBehavior
            )
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
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .fillMaxSize()
            ) {
                BestScores()
                GameRounds(navigateToRound)
            }
        }
    }
}

@Composable
fun BestScores() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(Color.LightGray.copy(0.11f))
            .border(BorderStroke(2.dp, Color.White)),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.best_scores),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.fillMaxWidth()
            )
            HorizontalDivider(
                thickness = dimensionResource(R.dimen.thickness_divider)
            )
            ScoreCard(R.drawable.first, "Piropo", 0)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ScoreCard(R.drawable.second, "Pilili", 26)
                ScoreCard(R.drawable.third, "Apuki Pu", 154)
            }
        }
    }
}

@Composable
fun ScoreCard(medal: Int, player: String, score: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray.copy(alpha = 0.3f)
        ),
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentSize()
        ) {
            Image(
                painterResource(medal),
                contentDescription = null,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .size(dimensionResource(R.dimen.image_extra_small))
            )
            Text(
                text = player,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSecondary,
            )
            Text(
                text = stringResource(R.string.player_score, score),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}

@Composable
fun GameRounds(navigateToRound: () -> Unit) {
    Text(
        text = stringResource(R.string.game_rounds),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_small))
            .fillMaxWidth()
    )
    HorizontalDivider(
        thickness = dimensionResource(R.dimen.thickness_divider),
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
    )
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        items(items = roundList) { item ->
            CardRound(round = item.round, isPlayed = item.isPlayed, navigateToRound = navigateToRound)
        }
    }
}

@Composable
fun CardRound(
    round: String,
    isPlayed: Boolean,
    navigateToRound: () -> Unit
    ) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.3f),
        ),
        shape = MaterialTheme.shapes.extraSmall,
        modifier = Modifier
            .wrapContentHeight()
            .padding(
                horizontal = dimensionResource(R.dimen.padding_extra_large)
            ),
        onClick = navigateToRound
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isPlayed) Icons.Rounded.Check else Icons.Rounded.Close,
                tint = if (isPlayed) Color.Green else errorContainerLightMediumContrast,
                modifier = Modifier
                    .size(32.dp)
                    .weight(1f),
                contentDescription = ""
            )
            Text(
                text = round,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(4f)
                    .padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameRoundsScreenPreview() {
    AppTheme {
        GameRoundsScreen()
    }
}