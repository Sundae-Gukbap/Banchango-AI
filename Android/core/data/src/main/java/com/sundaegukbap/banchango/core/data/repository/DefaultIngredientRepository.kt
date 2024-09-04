package com.sundaegukbap.banchango.core.data.repository

import com.sundaegukbap.banchango.IngredientContainer
import com.sundaegukbap.banchango.core.data.api.IngredientApi
import com.sundaegukbap.banchango.core.data.mapper.toData
import com.sundaegukbap.banchango.core.data.repository.api.IngredientRepository
import javax.inject.Inject

internal class DefaultIngredientRepository @Inject constructor(
    private val ingredientApi: IngredientApi,
) : IngredientRepository {
    override suspend fun getIngredientContainers(): Result<List<IngredientContainer>> {
        return runCatching {
            val response = ingredientApi.getIngredients(1)
            if (response.isSuccessful) {
                if (response.body() == null) {
                    throw IllegalStateException("Response body is null")
                }
                response.body()!!.map { it.toData() }
            } else {
                throw IllegalStateException("Response is not successful")
            }
        }
    }
}