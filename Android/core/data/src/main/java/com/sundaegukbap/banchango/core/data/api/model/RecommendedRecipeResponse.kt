package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class RecommendedRecipeResponse(
    val recipe: RecipeResponse,
    val have: HaveResponse,
    val need: NeedResponse,
)
