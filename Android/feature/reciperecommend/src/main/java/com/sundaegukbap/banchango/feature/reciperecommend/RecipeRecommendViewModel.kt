package com.sundaegukbap.banchango.feature.reciperecommend

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

    fun getRecipeRecommendation() {
        if (_uiState.value is RecipeRecommendUiState.Success) return

        viewModelScope.launch {
            recipeRepository.getRecipeRecommendation().onSuccess {
                _uiState.value =
                    RecipeRecommendUiState
                        .Success((it + it + it + it + it + it + it)
                            .mapIndexed { index, recipe ->
                                RecipeRecommendItemUiState(
                                    recipe = recipe.copy(name = recipe.name + index.toString()),
                                    isHated = false,
                                    isLiked = false,
                                )
                            })
            }.onFailure { throwable ->
                throwable.printStackTrace()
            }
        }
    }

    fun hateRecipe(page: Int) {
        val successRecipes = _uiState.value as? RecipeRecommendUiState.Success ?: return
        _uiState.value = RecipeRecommendUiState.Success(
            successRecipes.recipes.toMutableList().apply { removeAt(page) }
        )
    }
}
