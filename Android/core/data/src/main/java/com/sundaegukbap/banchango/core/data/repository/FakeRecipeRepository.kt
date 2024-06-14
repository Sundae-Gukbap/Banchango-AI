package com.sundaegukbap.banchango.core.data.repository

import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.core.data.repository.api.RecipeRepository
import javax.inject.Inject

class FakeRecipeRepository @Inject constructor() : RecipeRepository {
    override suspend fun getRecipeRecommendation(): Result<List<Recipe>> {
        return Result.success(
            listOf(
                Recipe(
                    id = 1,
                    name = "간장계란볶음밥",
                    introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                    image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                    link = "https://www.10000recipe.com/recipe/6889616",
                    cookingTime = 10,
                    servings = 2,
                    difficulty = "Easy",
                    have = listOf(""),
                    need = listOf(""),
                    isBookmarked = false,
                ),
                Recipe(
                    id = 2,
                    name = "간장계란볶음밥",
                    introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                    image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                    link = "https://www.10000recipe.com/recipe/6889616",
                    cookingTime = 10,
                    servings = 2,
                    difficulty = "Easy",
                    have = listOf(""),
                    need = listOf(""),
                    isBookmarked = false,
                ),

                Recipe(
                    id = 3,
                    name = "간장계란볶음밥",
                    introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                    image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                    link = "https://www.10000recipe.com/recipe/6889616",
                    cookingTime = 10,
                    servings = 2,
                    difficulty = "Easy",
                    have = listOf(""),
                    need = listOf(""),
                    isBookmarked = false,
                ),
            )
        )
    }

    override suspend fun getRecipeDetail(id: Long): Result<Recipe> {
        return Result.success(
            Recipe(
                id = id,
                name = "간장계란볶음밥",
                introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                link = "https://www.10000recipe.com/recipe/6889616",
                cookingTime = 10,
                servings = 2,
                difficulty = "Easy",
                have = listOf(""),
                need = listOf(""),
                isBookmarked = false,
            )
        )
    }
}
