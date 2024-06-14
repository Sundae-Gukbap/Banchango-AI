package com.sundaegukbap.banchango.feature.recipe.recommend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.core.data.repository.api.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeRecommendViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<RecipeRecommendUiState> =
        MutableStateFlow(RecipeRecommendUiState.Loading)
    val uiState: StateFlow<RecipeRecommendUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            recipeRepository.getRecipeRecommendation().onSuccess { recipes ->
                _uiState.value = RecipeRecommendUiState.Success(recipes.toUiState())
            }.onFailure { throwable ->
                throwable.printStackTrace()
            }
        }
    }

    fun getRecipeRecommendation() {
        val successUiState = _uiState.value as? RecipeRecommendUiState.Success ?: return

        viewModelScope.launch {
            recipeRepository.getRecipeRecommendation()
                .onSuccess { recipes ->
                    _uiState.value = RecipeRecommendUiState.Success(
                        successUiState.recipes + recipes.toUiState()
                    )
                }.onFailure { throwable ->
                    throwable.printStackTrace()
                }
        }
    }

    fun likeRecipe(recipe: Recipe) {
        val successUiState = _uiState.value as? RecipeRecommendUiState.Success ?: return
        val likedRecipeUiStates = successUiState.recipes.map { recipeUiState ->
            if (recipeUiState.recipe.id == recipe.id) {
                recipeUiState.copy(isLiked = true)
            } else {
                recipeUiState
            }
        }
        _uiState.value = RecipeRecommendUiState.Success(likedRecipeUiStates)
    }

    private fun List<Recipe>.toUiState(): List<RecipeRecommendItemUiState> {
        return mapIndexed { index, recipe ->
            RecipeRecommendItemUiState(
                recipe = recipe.copy(name = recipe.name + index.toString()),
                isLiked = false,
            )
        }
    }
}
