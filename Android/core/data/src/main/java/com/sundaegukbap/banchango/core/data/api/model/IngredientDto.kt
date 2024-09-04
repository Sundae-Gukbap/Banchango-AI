package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    val ingredientId: Long,
    val name: String,
    val kind: String,
    val image: String
)