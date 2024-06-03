package com.sundaegukbap.banchango.core.data.api

import com.sundaegukbap.banchango.core.data.api.model.RecipeRecommendResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface RecipeApi {
    @GET("api/recipe/recommand/{userId}")
    suspend fun getRecipeRecommendation(
        @Path("userId") userId: Long
    ): Response<List<RecipeRecommendResponse>>
}
