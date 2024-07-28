package com.sundaegukbap.banchango.core.data.mapper

import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.RecommendedRecipe
import com.sundaegukbap.banchango.core.data.api.model.IngredientResponse
import com.sundaegukbap.banchango.core.data.api.model.RecipeResponse
import com.sundaegukbap.banchango.core.data.api.model.RecommendedRecipeResponse
import com.sundaegukbap.banchango.core.data.api.model.RecommendedRecipesResponse

internal fun List<IngredientResponse>.toData() =
    map {
        Ingredient(
            id = it.id,
            name = it.name,
            image = it.image,
            kind =
                when (it.kind) {
                    "육류" -> IngredientKind.MEAT
                    "해산물" -> IngredientKind.SEAFOOD
                    "채소" -> IngredientKind.VEGETABLE
                    "과일" -> IngredientKind.FRUIT
                    "기타" -> IngredientKind.ETC
                    else -> IngredientKind.ETC
                },
        )
    }

internal fun RecommendedRecipesResponse.toData() = recommendedRecipeResponses.map { it.toData() }

internal fun RecommendedRecipeResponse.toData() =
    RecommendedRecipe(
        recipe = recipe.toData(),
        hadIngredients = have.ingredients.toData(),
        neededIngredients = need.ingredients.toData(),
    )

internal fun RecipeResponse.toData() =
    Recipe(
        id = id,
        name = name,
        introduction = introduction,
        image = image,
        link = link,
        servings = servings,
        cookingTime = cookingTime,
        difficulty =
            when (difficulty) {
                "아무나" -> RecipeDifficulty.ANYONE
                "초보" -> RecipeDifficulty.BEGINNER
                "중급" -> RecipeDifficulty.INTERMEDIATE
                "고급" -> RecipeDifficulty.ADVANCED
                "신의경지" -> RecipeDifficulty.GODLIKE
                else -> RecipeDifficulty.ANYONE
            },
    )
