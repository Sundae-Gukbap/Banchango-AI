package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.SerialName

data class NeedResponse(
    @SerialName("ingredientDtos")
    val ingredients: List<IngredientResponse>,
)
