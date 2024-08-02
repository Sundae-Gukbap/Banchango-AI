package com.sundaegukbap.banchango

data class Ingredient(
    val id: Long,
    val name: String,
    val kind: IngredientKind,
    val image: String,
)
