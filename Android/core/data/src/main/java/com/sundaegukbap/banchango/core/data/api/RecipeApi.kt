package com.sundaegukbap.banchango.core.data.api

import com.sundaegukbap.banchango.core.data.api.model.RecommendedRecipeResponse
import com.sundaegukbap.banchango.core.data.api.model.RecommendedRecipesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface RecipeApi {
    @GET("api/recipe/recommend/{userId}")
    suspend fun getRecipeRecommendation(
        @Path("userId") userId: Long,
    ): Response<RecommendedRecipesResponse>

    @GET("api/recipe/{userId}/{recipeId}")
    suspend fun getRecipeDetail(
        @Path("userId") userId: Long,
        @Path("recipeId") recipeId: Long,
    ): Response<RecommendedRecipeResponse>
}
