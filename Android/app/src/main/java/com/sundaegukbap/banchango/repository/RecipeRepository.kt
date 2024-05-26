package com.sundaegukbap.banchango.repository

import com.sundaegukbap.banchango.model.Recipe

interface RecipeRepository {
    fun getRecipeRecommendation(): List<Recipe>
}
