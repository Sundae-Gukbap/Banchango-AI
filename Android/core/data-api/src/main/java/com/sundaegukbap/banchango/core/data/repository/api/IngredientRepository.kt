package com.sundaegukbap.banchango.core.data.repository.api

import com.sundaegukbap.banchango.ContainerIngredient
import com.sundaegukbap.banchango.Ingredient
import java.time.LocalDateTime

interface IngredientRepository {
    suspend fun getIngredientContainers(): Result<List<ContainerIngredient>>
    suspend fun addIngredientContainer(containerName: String): Result<Unit>
    suspend fun addIngredientToContainer(
        containerId: Long,
        ingredientIds: List<Long>,
        expirationDate: LocalDateTime
    ): Result<Unit>

    suspend fun getAllIngredients(): Result<List<Ingredient>>
    suspend fun getIngredientsByNameLike(name: String): Result<List<Ingredient>>
}