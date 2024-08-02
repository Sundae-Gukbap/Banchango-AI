package com.sundaegukbap.banchango

data class LikableRecipe(
    val recommendedRecipe: RecommendedRecipe,
    val isLiked: Boolean,
)
