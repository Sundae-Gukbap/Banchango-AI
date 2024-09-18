package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ContainerIngredientDto(
    val containerIngredientId: Long,
    val containerDto: ContainerDto,
    val ingredientDto: IngredientDto,
    val createdAt: String,
    val expirationDate: String
)
