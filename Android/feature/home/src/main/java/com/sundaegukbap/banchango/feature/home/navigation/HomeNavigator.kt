package com.sundaegukbap.banchango.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sundaegukbap.banchango.feature.home.HomeRoute
import com.sundaegukbap.banchango.navigation.MainTabRoute
import com.sundaegukbap.banchango.navigation.Route

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    onChangeStatusBarColor: (color: Color, darkIcons: Boolean) -> Unit,
    showError: (String) -> Unit,
) {
    composable<MainTabRoute.Home> {
        HomeRoute(padding, onChangeStatusBarColor, showError)
    }
}
