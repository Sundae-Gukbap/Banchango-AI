package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeRecommendResponse(
    val id: Long,
    val name: String,
    val introduction: String,
    val image: String,
    val link: String,
    val have: List<String>,
    val need: List<String>,
    val servings: Int,
    val cookingTime: Int,
    val isBookmarked: Boolean,
    val difficulty: String
)
