package com.sundaegukbap.banchango.core.data.repository.api

import com.sundaegukbap.banchango.ContainerIngredient
import com.sundaegukbap.banchango.Ingredient

interface IngredientRepository {
    suspend fun getIngredientContainers(): Result<List<ContainerIngredient>>
    suspend fun addIngredientContainer(containerName: String): Result<Unit>
    suspend fun getAllIngredients(): Result<List<Ingredient>>
    suspend fun getIngredientsByNameLike(name: String): Result<List<Ingredient>>
}