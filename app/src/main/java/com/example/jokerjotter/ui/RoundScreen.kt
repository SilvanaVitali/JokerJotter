package com.example.jokerjotter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jokerjotter.JokerJotterTopAppBar
import com.example.jokerjotter.R
import com.example.jokerjotter.ui.navigation.NavigationDestination
import com.example.jokerjotter.ui.theme.AppTheme

object RoundDestination : NavigationDestination {
    override val route = "round_detail"
    override val titleRes = R.string.round_detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundScreen(
    navController: NavHostController = rememberNavController(),
    navigateBack: () -> Unit = {},
    editDetails: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val background = painterResource(R.drawable.backgroundapp)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        topBar = {
            JokerJotterTopAppBar(
                title = "Juego 1",
                canNavigateBack = true,
                canSave = false,
                scrollBehavior = scrollBehavior,
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
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
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(
                    text = "2 Tríos - 1 Escala",
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
                    GoalRoundSection()
                    PlayersScoreSection(editDetails = editDetails)
                }
            }
        }
    }
}

@Composable
fun GoalRoundSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        ) {
            HandGame(
                image = painterResource(R.drawable.trio_1),
                description = stringResource(R.string.three_of)
            )
            HandGame(
                image = painterResource(R.drawable.trio_2),
                description = stringResource(R.string.three_of)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        ) {
            HandGame(
                image = painterResource(R.drawable.escala_1),
                description = stringResource(R.string.straight)
            )
//            HandGame(
//                image = painterResource(R.drawable.one_eyed),
//                description = stringResource(R.string.one_eyed),
//                points = "15"
//            )
        }
    }
}

@Composable
fun HandGame(image: Painter, description: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            vertical = dimensionResource(R.dimen.padding_small)
        )
    ) {
        Image(
            painter = image,
            contentDescription = description,
            modifier = Modifier.height(dimensionResource(R.dimen.image_medium))
        )
    }
}

@Composable
fun PlayersScoreSection(
    editDetails: () -> Unit = {},
) {
    Text(
        text = stringResource(R.string.score),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSecondary
    )
    HorizontalDivider(
        thickness = dimensionResource(R.dimen.thickness_divider),
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
    )
    LazyColumn {
        items(items = players) { player ->
            val score = player.length * 10 - 40
            PlayerPoints(player = player, editDetails = editDetails, score = score.toString())
            HorizontalDivider()
        }
    }
}

@Composable
fun PlayerPoints(
    player: String,
    score: String = "-",
    editDetails: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_small))
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = player,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = score,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
            )
            IconButton(
                onClick = editDetails
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.edit_item_title),
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RoundScreenPreview() {
    AppTheme {
        RoundScreen()
    }
}