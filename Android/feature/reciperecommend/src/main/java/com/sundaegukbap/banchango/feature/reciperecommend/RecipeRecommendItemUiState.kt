package com.sundaegukbap.banchango.feature.reciperecommend

import com.sundaegukbap.banchango.Recipe

data class RecipeRecommendItemUiState(
    val recipe: Recipe,
    val isHated: Boolean,
    val isLiked: Boolean,
)
