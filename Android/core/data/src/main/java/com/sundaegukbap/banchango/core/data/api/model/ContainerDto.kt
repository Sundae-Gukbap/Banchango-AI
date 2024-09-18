package com.sundaegukbap.banchango.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ContainerDto(
    val containerId: Long,
    val containerName: String
)