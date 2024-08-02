package com.sundaegukbap.banchango

data class RecommendedRecipe(
    val recipe: Recipe,
    val hadIngredients: List<Ingredient>,
    val neededIngredients: List<Ingredient>,
)
