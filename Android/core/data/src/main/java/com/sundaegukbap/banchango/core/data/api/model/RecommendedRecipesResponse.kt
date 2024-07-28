package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.SerialName

data class RecommendedRecipesResponse(
    @SerialName("recommandedRecipeResponses")
    val recommendedRecipeResponses: List<RecommendedRecipeResponse>,
)
