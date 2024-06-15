package com.sundaegukbap.banchango.feature.recipe.recommend

import com.sundaegukbap.banchango.Recipe

data class RecipeRecommendItemUiState(
    val recipe: Recipe,
    val isLiked: Boolean,
)
