package com.sundaegukbap.banchango.core.data.repository.api

import com.sundaegukbap.banchango.Recipe

interface RecipeRepository {
    fun getRecipeRecommendation(): List<Recipe>
}
