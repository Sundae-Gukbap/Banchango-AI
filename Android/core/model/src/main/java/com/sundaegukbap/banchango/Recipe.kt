package com.sundaegukbap.banchango

data class Recipe(
    val id: Long,
    val name: String,
    val introduction: String,
    val image: String,
    val link: String,
    val cookingTime: Int,
    val servings: Int,
    val difficulty: String,
    val isBookmarked: Boolean,
    val have: List<String>,
    val need: List<String>
)
