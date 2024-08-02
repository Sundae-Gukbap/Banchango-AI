package com.sundaegukbap.banchango.core.data.repository.api

import com.sundaegukbap.banchango.RecommendedRecipe

interface RecipeRepository {
    suspend fun getRecipeRecommendation(): Result<List<RecommendedRecipe>>

    suspend fun getRecipeDetail(id: Long): Result<RecommendedRecipe>

    suspend fun likeRecipe(
        id: Long,
        isLiked: Boolean,
    ): Result<Unit>
}
