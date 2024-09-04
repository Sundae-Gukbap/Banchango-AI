package com.sundaegukbap.banchango

data class IngredientContainer(
    val id: Long,
    val container: Container,
    val ingredient: Ingredient,
    val createdAt: String,
    val expirationDate: String
)

