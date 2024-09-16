package com.sundaegukbap.banchango

import java.time.LocalDateTime

data class ContainerIngredient(
    val id: Long,
    val container: Container,
    val ingredient: Ingredient,
    val createdAt: LocalDateTime,
    val expirationDate: LocalDateTime
)

