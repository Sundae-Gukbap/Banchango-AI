package com.sundaegukbap.banchango.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sundaegukbap.banchango.feature.home.HomeScreen
import com.sundaegukbap.banchango.navigation.MainTabRoute

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    onChangeSystemBarsColor: (color: Color, darkIcons: Boolean) -> Unit,
) {
    composable<MainTabRoute.Home> {
        HomeScreen(padding, onChangeSystemBarsColor)
    }
}
