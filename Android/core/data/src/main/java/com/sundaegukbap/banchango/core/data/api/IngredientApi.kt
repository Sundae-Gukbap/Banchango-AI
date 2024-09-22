package com.sundaegukbap.banchango.core.data.api

import com.sundaegukbap.banchango.core.data.api.model.AddIngredientContainerRequest
import com.sundaegukbap.banchango.core.data.api.model.ContainerIngredientDtos
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface IngredientApi {
    @GET("api/ingredients/main/list/{userid}")
    suspend fun getIngredients(
        @Path("userid") userId: Long,
    ): Result<ContainerIngredientDtos>

    @POST("/api/container/{userId}")
    suspend fun addIngredientContainer(
        @Path("userId") userId: Long,
        @Body addIngredientContainerRequest: AddIngredientContainerRequest,
    ): Result<Unit>
}

