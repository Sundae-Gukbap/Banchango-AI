package com.sundaegukbap.banchango.core.data.repository

import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.core.data.api.RecipeApi
import com.sundaegukbap.banchango.core.data.api.model.RecipeRecommendResponse
import com.sundaegukbap.banchango.core.data.mapper.toData
import com.sundaegukbap.banchango.core.data.repository.api.RecipeRepository
import javax.inject.Inject

internal class DefaultRecipeRepository @Inject constructor(
    private val recipeApi: RecipeApi,
) : RecipeRepository {
    override suspend fun getRecipeRecommendation(): Result<List<Recipe>> {
        return runCatching {
            val response = recipeApi.getRecipeRecommendation(1)
            if (response.isSuccessful) {
                if (response.body() == null) {
                    throw IllegalStateException("Response body is null")
                }
                response.body()!!.toData()
            } else {
                throw IllegalStateException("Response is not successful")
            }
        }
    }
}
