package com.sundaegukbap.banchango.feature.reciperecommend

sealed interface RecipeRecommendUiState {
    data object Loading : RecipeRecommendUiState
    data class Success(val recipes: List<RecipeRecommendItemUiState>) :
        RecipeRecommendUiState
}
