package com.sundaegukbap.banchango.core.data.repository

import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.RecommendedRecipe
import com.sundaegukbap.banchango.core.data.repository.api.RecipeRepository
import javax.inject.Inject

class FakeRecipeRepository
    @Inject
    constructor() : RecipeRepository {
        override suspend fun getRecipeRecommendation(): Result<List<RecommendedRecipe>> =
            Result.success(
                List(10) {
                    RecommendedRecipe(
                        Recipe(
                            id = (it + 1).toLong(),
                            name = "간장계란볶음밥",
                            introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                            image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                            link = "https://www.10000recipe.com/recipe/6889616",
                            cookingTime = 10,
                            servings = 2,
                            difficulty = RecipeDifficulty.BEGINNER,
                        ),
                        hadIngredients =
                            listOf(
                                Ingredient(1L, "계란", IngredientKind.ETC, ""),
                                Ingredient(1L, "간장", IngredientKind.ETC, ""),
                            ),
                        neededIngredients = listOf(Ingredient(1L, "참기름", IngredientKind.ETC, "")),
                    )
                },
            )

        override suspend fun getRecipeDetail(id: Long): Result<RecommendedRecipe> =
            Result.success(
                RecommendedRecipe(
                    Recipe(
                        id = 1,
                        name = "간장계란볶음밥",
                        introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                        image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                        link = "https://www.10000recipe.com/recipe/6889616",
                        cookingTime = 10,
                        servings = 2,
                        difficulty = RecipeDifficulty.BEGINNER,
                    ),
                    hadIngredients =
                        listOf(
                            Ingredient(1L, "계란", IngredientKind.ETC, ""),
                            Ingredient(1L, "간장", IngredientKind.ETC, ""),
                        ),
                    neededIngredients = listOf(Ingredient(1L, "참기름", IngredientKind.ETC, "")),
                ),
            )

        override suspend fun likeRecipe(
            id: Long,
            isLiked: Boolean,
        ): Result<Unit> = Result.success(Unit)
    }
