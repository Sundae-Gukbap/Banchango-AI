package com.sundaegukbap.banchango.core.data.api

import com.sundaegukbap.banchango.core.data.api.model.ContainerIngredientDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface IngredientApi {
    @GET("api/ingredients/main/list/{userid}")
    suspend fun getIngredients(
        @Path("userid") userId: Long,
    ): Response<List<ContainerIngredientDto>>
}