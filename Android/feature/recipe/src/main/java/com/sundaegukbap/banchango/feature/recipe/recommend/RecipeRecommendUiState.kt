package com.sundaegukbap.banchango.feature.recipe.recommend

sealed interface RecipeRecommendUiState {
    data object Loading : RecipeRecommendUiState
    data class Success(val recipes: List<RecipeRecommendItemUiState>) :
        RecipeRecommendUiState
}
