package com.example.jokerjotter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokerjotter.JokerJotterTopAppBar
import com.example.jokerjotter.R
import com.example.jokerjotter.ui.navigation.NavigationDestination
import com.example.jokerjotter.ui.theme.AppTheme
import com.example.jokerjotter.ui.theme.fontFamily

object GameEditDestination : NavigationDestination {
    override val route = "game_edit"
    override val titleRes = R.string.game_setup
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameEditScreen(
    navigateBack: () -> Unit = {},
    saveDetails: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val background = painterResource(R.drawable.backgroundapp)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier,
        topBar = {
            JokerJotterTopAppBar(
                title = stringResource(R.string.game_setup),
                canNavigateBack = true,
                canSave = true,
                scrollBehavior = scrollBehavior,
                navigateBack = navigateBack,
                saveDetails = saveDetails
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = background,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            GameEditBody(innerPadding)
        }
    }
}

@Composable
fun GameEditBody(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
    ) {
        var nameGame by rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
        ) {
            OutlinedTextField(
                value = nameGame,
                onValueChange = { nameGame = it },
                label = {
                    Text(
                        text = stringResource(R.string.name_game),
                        fontFamily = fontFamily,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.name_game),
                        fontFamily = fontFamily,
                        color = MaterialTheme.colorScheme.tertiaryContainer
                    )
                },
                colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
                    focusedTextColor = MaterialTheme.colorScheme.onSecondary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSecondary
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineSmall
            )
            EditCardPoints()
            EditPlayers()
        }
    }
}

@Composable
fun EditCardPoints() {
    Text(
        text = stringResource(R.string.score_cards),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
    )
    HorizontalDivider(
        thickness = dimensionResource(R.dimen.thickness_divider),
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
    )
    InputCardPoints(
        image = painterResource(R.drawable.as_card),
        description = stringResource(R.string.as_card),
        cardLabel = stringResource(
            R.string.points_input,
            stringResource(R.string.as_card)
        )
    )
    InputCardPoints(
        image = painterResource(R.drawable.one_eyed),
        description = stringResource(R.string.one_eyed),
        cardLabel = stringResource(
            R.string.points_input,
            stringResource(R.string.one_eyed)
        )
    )
    InputCardPoints(
        image = painterResource(R.drawable.jokers),
        description = stringResource(R.string.joker),
        cardLabel = stringResource(
            R.string.points_input,
            stringResource(R.string.joker)
        )
    )
}

@Composable
fun InputCardPoints(image: Painter, description: String, cardLabel: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            vertical = dimensionResource(R.dimen.padding_small),
            horizontal = dimensionResource(R.dimen.padding_extra_large)
        )
    ) {
        var valueInput by rememberSaveable { mutableStateOf("") }
        Image(
            painter = image,
            contentDescription = description,
            modifier = Modifier
                .size(dimensionResource(R.dimen.image_small))
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_large)))
        Text(
            text = "=",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_large)))
        OutlinedTextField(
            value = valueInput,
            onValueChange = { valueInput = it },
            label = {
                Text(
                    text = cardLabel,
                    fontFamily = fontFamily,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            placeholder = {
                Text(
                    text = cardLabel,
                    fontFamily = fontFamily,
                    color = MaterialTheme.colorScheme.tertiaryContainer
                )
            },
            colors =
            OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
                focusedTextColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSecondary
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            textStyle = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun EditPlayers() {
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
        items(8) { index ->
            PlayerInput(id = index + 1)
        }
    }
}

@Composable
fun PlayerInput(id: Int) {
    var playerInput by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = playerInput,
        onValueChange = { playerInput = it },
        label = {
            Text(
                text = stringResource(R.string.player_id, id),
                fontFamily = fontFamily,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.player_id, id),
                fontFamily = fontFamily,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
            focusedTextColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSecondary
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge
    )
}

@Preview(showBackground = true)
@Composable
fun GameEditScreenPreview() {
    AppTheme {
        GameEditScreen()
    }
}