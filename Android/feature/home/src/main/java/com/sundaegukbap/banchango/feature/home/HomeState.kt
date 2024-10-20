package com.sundaegukbap.banchango.feature.home

import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientContainer
import com.sundaegukbap.banchango.KindIngredientContainer

data class HomeState(
    val ingredientContainers: List<IngredientContainer> = emptyList(),
    val kindIngredientContainerDetail: KindIngredientContainer? = null,
    val ingredients: List<Ingredient> = emptyList(),
    val isLoading: Boolean = false,
    val isDetailShowing: Boolean = false,
)