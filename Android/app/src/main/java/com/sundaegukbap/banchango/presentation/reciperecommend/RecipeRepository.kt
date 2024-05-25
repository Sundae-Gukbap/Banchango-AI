package com.sundaegukbap.banchango.presentation.reciperecommend

interface RecipeRepository {
    fun getRecipeRecommendation(): List<Recipe>
}
