package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class AddIngredientContainerRequest(
    val containerName: String,
)