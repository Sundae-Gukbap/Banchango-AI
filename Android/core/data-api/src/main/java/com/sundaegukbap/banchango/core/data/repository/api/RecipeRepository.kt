package com.sundaegukbap.banchango.core.data.repository.api

import com.sundaegukbap.banchango.Recipe

interface RecipeRepository {
    suspend fun getRecipeRecommendation(): Result<List<Recipe>>
    suspend fun getRecipeDetail(id: Long): Result<Recipe>
}
