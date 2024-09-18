package com.sundaegukbap.banchango.core.data.repository

import android.util.Log
import com.sundaegukbap.banchango.Container
import com.sundaegukbap.banchango.ContainerIngredient
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.core.data.api.IngredientApi
import com.sundaegukbap.banchango.core.data.mapper.toData
import com.sundaegukbap.banchango.core.data.repository.api.IngredientRepository
import java.time.LocalDateTime
import javax.inject.Inject

internal class DefaultIngredientRepository @Inject constructor(
    private val ingredientApi: IngredientApi,
) : IngredientRepository {
    override suspend fun getIngredientContainers(): Result<List<ContainerIngredient>> {
        return runCatching {
            val response = ingredientApi.getIngredients(1)
            Log.d("asdf", "response: $response")
            if (response.isSuccessful) {
                if (response.body() == null) {
                    throw IllegalStateException("Response body is null")
                }
                response.body()!!.containerIngredientDtos.map { it.toData() }
            } else {
                throw IllegalStateException("Response is not successful")
            }
        }
    }
}
