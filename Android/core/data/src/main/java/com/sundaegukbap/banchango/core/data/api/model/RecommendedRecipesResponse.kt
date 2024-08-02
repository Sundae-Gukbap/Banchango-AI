package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedRecipesResponse(
    @SerialName("recommandedRecipeResponses")
    val recommendedRecipeResponses: List<RecommendedRecipeResponse>,
)
