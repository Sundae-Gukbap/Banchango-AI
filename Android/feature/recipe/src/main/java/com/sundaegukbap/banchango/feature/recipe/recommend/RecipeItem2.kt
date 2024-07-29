package com.sundaegukbap.banchango.feature.recipe.recommend

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.RecommendedRecipe
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme

@Composable
fun RecipeItem2(
    recipeItemUiState: RecipeRecommendItemUiState,
    onRecipeClick: (Recipe) -> Unit,
    onRecipeLikeClick: (Recipe) -> Unit,
) {

}

@Preview(showBackground = false)
@Composable
private fun RecipeItemPreview() {
    BanchangoTheme {
        RecipeItem2(
            recipeItemUiState =
                RecipeRecommendItemUiState(
                    recommendedRecipe =
                    RecommendedRecipe(
                        recipe =
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
                            Ingredient(1L, "계란", IngredientKind.MEAT, ""),
                            Ingredient(2L, "간장", IngredientKind.SAUCE, ""),
                        ),
                        neededIngredients =
                        listOf(
                            Ingredient(1L, "식초", IngredientKind.MEAT, ""),
                            Ingredient(2L, "당근", IngredientKind.SAUCE, ""),
                            Ingredient(2L, "감자", IngredientKind.SAUCE, ""),
                        ),
                    ),
                    isLiked = false,
                ),
            onRecipeClick = {},
            onRecipeLikeClick = {},
        )
    }
}
