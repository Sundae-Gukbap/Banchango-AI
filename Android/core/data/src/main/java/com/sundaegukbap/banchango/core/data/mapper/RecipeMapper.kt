package com.sundaegukbap.banchango.core.data.mapper

import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.core.data.api.model.RecipeRecommendResponse

internal fun List<RecipeRecommendResponse>.toData(): List<Recipe> = map { it.toData() }

internal fun RecipeRecommendResponse.toData(): Recipe {
    val difficulty = when (difficulty) {
        "아무나" -> RecipeDifficulty.ANYONE
        "초보" -> RecipeDifficulty.BEGINNER
        "중급" -> RecipeDifficulty.INTERMEDIATE
        "고급" -> RecipeDifficulty.ADVANCED
        "신의경지" -> RecipeDifficulty.GODLIKE
        else -> RecipeDifficulty.ANYONE
    }
    return Recipe(
        id = id,
        name = name,
        introduction = introduction,
        image = image,
        link = link,
        have = have,
        need = need,
        servings = servings,
        cookingTime = cookingTime,
        difficulty = difficulty,
    )
}
