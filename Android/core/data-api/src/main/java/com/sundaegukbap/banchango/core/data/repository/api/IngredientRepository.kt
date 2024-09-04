package com.sundaegukbap.banchango.core.data.repository.api

import com.sundaegukbap.banchango.IngredientContainer

interface IngredientRepository {
    suspend fun getIngredientContainers(): Result<List<IngredientContainer>>
}