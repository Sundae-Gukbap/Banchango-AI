package com.sundaegukbap.banchango.presentation.reciperecommend

data class Recipe(
    val id: Long,
    val name: String,
    val introduction: String,
    val image: String,
    val link: String,
    val cookingTime: Int,
    val servings: Int,
    val difficulty: String,
    val have: List<Int>,
    val need: List<Int>
)
