package com.sundaegukbap.banchango.feature.reciperecommend

import androidx.lifecycle.ViewModel
import com.sundaegukbap.banchango.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RecipeRecommendViewModel @Inject constructor() : ViewModel() {

    private val _recipes: MutableStateFlow<List<Recipe>> = MutableStateFlow(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    fun getRecipeRecommendation() {
        _recipes.value = List(10) {
            Recipe(
                id = (it + 1).toLong(),
                name = "간장계란볶음밥",
                introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                link = "https://www.10000recipe.com/recipe/6889616",
                cookingTime = 10,
                servings = 2,
                difficulty = "Easy",
                have = listOf(1, 2, 3, 4, 5),
                need = listOf(6, 7, 8, 9, 10)
            )
        }
    }
}
