package com.sundaegukbap.banchango.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data class RecipeDetail(val recipeId: Long) : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object RecipeRecommend : MainTabRoute

    @Serializable
    data object Home : MainTabRoute
}
