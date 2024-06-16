package com.sundaegukbap.banchango.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import com.sundaegukbap.banchango.feature.home.navigation.homeNavGraph
import com.sundaegukbap.banchango.feature.recipe.navigation.recipeNavGraph
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    onChangeDarkTheme: (Boolean) -> Unit,
    onChangeSystemBarsColor: (color: Color, darkIcons: Boolean) -> Unit
) {
    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination
                ) {
                    homeNavGraph(
                        padding = padding,
                        onChangeSystemBarsColor = onChangeSystemBarsColor
                    )
                    recipeNavGraph(
                        padding = padding,
                        onRecipeClick = { navigator.navigateRecipeDetail(it.id) },
                        onChangeSystemBarsColor = onChangeSystemBarsColor
                    )
                }
            }
        },
        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        }
    )
}
