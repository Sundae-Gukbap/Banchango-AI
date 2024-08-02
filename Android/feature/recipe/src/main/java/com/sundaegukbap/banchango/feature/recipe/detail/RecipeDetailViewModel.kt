package com.sundaegukbap.banchango.feature.recipe.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sundaegukbap.banchango.LikableRecipe
import com.sundaegukbap.banchango.core.data.repository.api.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel
    @Inject
    constructor(
        private val recipeRepository: RecipeRepository,
    ) : ViewModel() {
        private val _uiState: MutableStateFlow<RecipeDetailUiState> =
            MutableStateFlow(RecipeDetailUiState.Loading)
        val uiState: StateFlow<RecipeDetailUiState> = _uiState.asStateFlow()

        fun getRecipeDetail(recipeId: Long) {
            viewModelScope.launch {
                recipeRepository
                    .getRecipeDetail(recipeId)
                    .onSuccess { recommendedRecipe ->
                        _uiState.value =
                            RecipeDetailUiState.Success(LikableRecipe(recommendedRecipe, false))
                    }.onFailure { throwable ->
                        _uiState.value = RecipeDetailUiState.Error(throwable)
                    }
            }
        }

        fun likeRecipe(
            recipeId: Long,
            isLiked: Boolean,
        ) {
            val successUiState = uiState.value as? RecipeDetailUiState.Success ?: return
            viewModelScope.launch {
                recipeRepository.likeRecipe(recipeId, isLiked)
                _uiState.value =
                    RecipeDetailUiState.Success(successUiState.likableRecipe.copy(isLiked = isLiked))
            }
        }
    }
