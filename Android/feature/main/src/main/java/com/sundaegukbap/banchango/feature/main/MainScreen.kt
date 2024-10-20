package com.sundaegukbap.banchango.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.sundaegukbap.banchango.feature.home.navigation.homeNavGraph
import com.sundaegukbap.banchango.feature.recipe.navigation.recipeNavGraph
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    onChangeDarkTheme: (Boolean) -> Unit,
    onChangeStatusBarColor: (color: Color, darkIcons: Boolean) -> Unit
) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
                        onChangeStatusBarColor = onChangeStatusBarColor,
                        showError = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = it,
                                    actionLabel = "Action",
                                    duration = SnackbarDuration.Indefinite
                                )
                            }
                        },
                    )
                    recipeNavGraph(
                        padding = padding,
                        onRecipeClick = { navigator.navigateRecipeDetail(it.id) },
                        onChangeStatusBarColor = onChangeStatusBarColor
                    )
                }
            }
        },
        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) },
                modifier = Modifier
                    .navigationBarsPadding()
            )
        }
    )
}
