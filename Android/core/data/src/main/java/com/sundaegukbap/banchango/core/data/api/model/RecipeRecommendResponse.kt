package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeRecommendsResponse(
    val recipeRecommends: List<RecipeRecommendResponse>
)
