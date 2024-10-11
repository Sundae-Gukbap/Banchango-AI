package com.sundaegukbap.banchango.core.data.repository.api

import com.sundaegukbap.banchango.ContainerIngredient

interface IngredientRepository {
    suspend fun getIngredientContainers(): Result<List<ContainerIngredient>>
    suspend fun addIngredientContainer(containerName: String): Result<Unit>
}