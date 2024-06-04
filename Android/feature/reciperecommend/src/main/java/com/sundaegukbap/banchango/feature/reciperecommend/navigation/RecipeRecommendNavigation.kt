package com.sundaegukbap.banchango.feature.reciperecommend.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sundaegukbap.banchango.feature.reciperecommend.RecipeRecommendRoute
import com.sundaegukbap.banchango.navigation.MainTabRoute

fun NavController.navigateRecipeRecommend(navOptions: NavOptions) {
    navigate(MainTabRoute.RecipeRecommend, navOptions)
}

fun NavGraphBuilder.recipeRecommendNavGraph(padding: PaddingValues) {
    composable<MainTabRoute.RecipeRecommend> {
        RecipeRecommendRoute(padding)
    }
}
