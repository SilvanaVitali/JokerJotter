package com.example.jokerjotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokerjotter.ui.HomeScreen
import com.example.jokerjotter.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                    HomeScreen()
                }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JokerJotterPreview() {
    AppTheme {
        HomeScreen()
    }
}