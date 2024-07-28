package com.sundaegukbap.banchango.core.data.repository

import com.sundaegukbap.banchango.LikableRecipe
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.core.data.api.RecipeApi
import com.sundaegukbap.banchango.core.data.mapper.toData
import com.sundaegukbap.banchango.core.data.repository.api.RecipeRepository
import javax.inject.Inject

internal class DefaultRecipeRepository
    @Inject
    constructor(
        private val recipeApi: RecipeApi,
    ) : RecipeRepository {
        override suspend fun getRecipeRecommendation(): Result<List<Recipe>> =
            runCatching {
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

        override suspend fun getRecipeDetail(id: Long): Result<LikableRecipe> =
            runCatching {
                val response = recipeApi.getRecipeDetail(1, id)
                if (response.isSuccessful) {
                    if (response.body() == null) {
                        throw IllegalStateException("Response body is null")
                    }
                    LikableRecipe(response.body()!!.toData(), false)
                } else {
                    throw IllegalStateException("Response is not successful")
                }
            }

        override suspend fun likeRecipe(
            id: Long,
            isLiked: Boolean,
        ): Result<Unit> {
            TODO("호감 기능 추가시 구현")
        }
    }
