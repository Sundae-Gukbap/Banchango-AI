package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val id: Long,
    val name: String,
    val introduction: String,
    val image: String,
    val link: String,
    val servings: Int,
    val cookingTime: Int,
    val difficulty: String,
)
