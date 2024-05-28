package com.sundaegukbap.banchango.presentation.reciperecommend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sundaegukbap.banchango.ui.theme.BanchangoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RecipeRecommendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.getRecipeRecommendation()
        setContent {
            BanchangoTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color(0xBFFFFFFF), darkIcons = true)
                systemUiController.setNavigationBarColor(
                    color = Color(0xFFFFFFFF)
                )
                RecipesRecommendScreen()
            }
        }
    }
}
