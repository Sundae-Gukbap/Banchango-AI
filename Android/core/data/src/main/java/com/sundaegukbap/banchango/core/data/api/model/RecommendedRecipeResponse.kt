package com.sundaegukbap.banchango.core.data.api.model

data class RecommendedRecipeResponse(
    val recipe: RecipeResponse,
    val have: HaveResponse,
    val need: NeedResponse,
)
