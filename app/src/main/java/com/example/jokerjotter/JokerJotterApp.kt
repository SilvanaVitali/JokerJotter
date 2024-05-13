package com.example.jokerjotter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jokerjotter.ui.HomeDestination
import com.example.jokerjotter.ui.navigation.JokerJotterNavHost

@Composable
fun JokerJotterApp(navController: NavHostController = rememberNavController(), startDestination: String = HomeDestination.route) {
    JokerJotterNavHost(navController = navController, startDestination = startDestination)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokerJotterTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    canSave: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateBack: () -> Unit = {},
    saveDetails: () -> Unit = {}
) {
    Box {
        Image(
            painter = painterResource(R.drawable.topbar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier.height(56.dp)
        )
        TopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            colors =
            TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            },
            actions = {
                if (canSave) {
                    IconButton(onClick = saveDetails) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = stringResource(R.string.save_button),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        )
    }
}