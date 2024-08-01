package com.sundaegukbap.banchango.feature.recipe.recommend

import com.sundaegukbap.banchango.RecommendedRecipe

data class RecipeRecommendItemUiState(
    val recommendedRecipe: RecommendedRecipe,
    val isLiked: Boolean,
) {
    val hadCount = recommendedRecipe.hadIngredients.count()
    val needCount = recommendedRecipe.neededIngredients.count()
}
