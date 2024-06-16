package com.sundaegukbap.banchango.feature.recipe.detail

import com.sundaegukbap.banchango.Recipe

sealed interface RecipeDetailUiState {
    data object Loading : RecipeDetailUiState
    data class Success(val recipe: Recipe) : RecipeDetailUiState
    data class Error(val throwable: Throwable) : RecipeDetailUiState
}
