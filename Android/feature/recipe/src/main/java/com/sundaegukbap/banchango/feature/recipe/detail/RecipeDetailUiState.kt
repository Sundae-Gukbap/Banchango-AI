package com.sundaegukbap.banchango.feature.recipe.detail

import com.sundaegukbap.banchango.LikableRecipe

sealed interface RecipeDetailUiState {
    data object Loading : RecipeDetailUiState

    data class Success(
        val likableRecipe: LikableRecipe,
    ) : RecipeDetailUiState

    data class Error(
        val throwable: Throwable,
    ) : RecipeDetailUiState
}
