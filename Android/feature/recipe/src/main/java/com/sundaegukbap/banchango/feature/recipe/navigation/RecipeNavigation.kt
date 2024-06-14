package com.sundaegukbap.banchango.feature.recipe.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.feature.recipe.recommend.RecipeRecommendRoute
import com.sundaegukbap.banchango.navigation.MainTabRoute
import com.sundaegukbap.banchango.navigation.Route

fun NavController.navigateRecipeRecommend(navOptions: NavOptions) {
    navigate(MainTabRoute.RecipeRecommend, navOptions)
}

fun NavController.navigateRecipeDetail(recipeId: Long) {
    navigate(Route.RecipeDetail(recipeId))

}

fun NavGraphBuilder.recipeNavGraph(
    padding: PaddingValues,
    onRecipeClick: (Recipe) -> Unit
) {
    composable<MainTabRoute.RecipeRecommend> {
        RecipeRecommendRoute(
            padding = padding,
            onRecipeClick = onRecipeClick
        )
    }

    composable<Route.RecipeDetail> { navBackStackEntry ->
        val recipeId = navBackStackEntry.toRoute<Route.RecipeDetail>().recipeId
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "하이 $recipeId")
        }
    }
}
