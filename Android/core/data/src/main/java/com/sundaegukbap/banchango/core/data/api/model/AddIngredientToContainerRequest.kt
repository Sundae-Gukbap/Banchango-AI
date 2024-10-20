package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddIngredientToContainerRequest(
    @SerialName("containerId") val containerId: Long,
    @SerialName("ingredientId")val ingredientId: Long,
    @SerialName("expirationDate") val expirationDate: String,
)