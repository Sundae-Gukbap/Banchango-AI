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

    private val _recipes: MutableStateFlow<List<Recipe>> = MutableStateFlow(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    fun getRecipeRecommendation() {
        viewModelScope.launch {
            recipeRepository.getRecipeRecommendation().onSuccess {
                _recipes.value = (it + it + it + it + it + it + it)
                    .mapIndexed { index, value ->
                        value.copy(name = value.name + "$index")
                    }
            }.onFailure { throwable ->
                throwable.printStackTrace()
            }
        }
    }

    fun hateRecipe(page: Int) {
        _recipes.value = _recipes.value.toMutableList().apply { removeAt(page) }
    }
}
