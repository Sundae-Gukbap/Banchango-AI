package com.sundaegukbap.banchango.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanchangoTheme {
                val navController = rememberMainNavigator()

                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color(0xBFFFFFFF), darkIcons = true)
                systemUiController.setNavigationBarColor(
                    color = Color(0xFFFFFFFF)
                )

                MainScreen(
                    navigator = navController,
                    onChangeDarkTheme = {}
                )
            }
        }
    }
}
