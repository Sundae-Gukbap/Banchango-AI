package com.sundaegukbap.banchango.core.data.mapper

import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.core.data.api.model.RecipeRecommendResponse

internal fun List<RecipeRecommendResponse>.toData(): List<Recipe> = map { it.toData() }

internal fun RecipeRecommendResponse.toData(): Recipe {
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
        isBookmarked = isBookmarked,
        difficulty = difficulty,
    )
}
