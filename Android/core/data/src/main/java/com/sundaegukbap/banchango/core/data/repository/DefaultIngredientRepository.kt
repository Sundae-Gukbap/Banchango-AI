package com.sundaegukbap.banchango.core.data.repository

import com.sundaegukbap.banchango.ContainerIngredient
import com.sundaegukbap.banchango.core.data.api.IngredientApi
import com.sundaegukbap.banchango.core.data.api.model.AddIngredientContainerRequest
import com.sundaegukbap.banchango.core.data.mapper.toData
import com.sundaegukbap.banchango.core.data.repository.api.IngredientRepository
import javax.inject.Inject

internal class DefaultIngredientRepository @Inject constructor(
    private val ingredientApi: IngredientApi,
) : IngredientRepository {
    override suspend fun getIngredientContainers(): Result<List<ContainerIngredient>> {
        return ingredientApi.getIngredients(1).mapCatching { dto ->
            dto.containerIngredientDtos.map { it.toData() }
        }
    }

    override suspend fun addIngredientContainer(containerName: String): Result<Unit> {
        return ingredientApi.addIngredientContainer(1, AddIngredientContainerRequest(containerName))
    }
}
