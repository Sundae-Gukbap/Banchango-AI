package com.sundaegukbap.banchango.feature.recipe.recommend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.RecommendedRecipe
import com.sundaegukbap.banchango.core.designsystem.component.NetworkImage
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme
import com.sundaegukbap.banchango.core.designsystem.theme.Orange
import com.sundaegukbap.banchango.core.designsystem.theme.lightGray
import com.sundaegukbap.banchango.feature.recipe.extrainfo.RecipeExtraInfo
import com.sundaegukbap.banchango.feature.reciperecommend.R

@Composable
fun RecipeItem(
    recipeRecommendItemUiState: RecipeRecommendItemUiState,
    onRecipeClick: (Recipe) -> Unit,
    onRecipeLikeClick: (Recipe) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable {
                onRecipeClick(recipeRecommendItemUiState.recommendedRecipe.recipe)
            }
            .shadow(4.dp, RoundedCornerShape(20.dp))
            .background(Color.White, shape = RoundedCornerShape(20.dp))
    ) {
        NetworkImage(
            url = recipeRecommendItemUiState.recommendedRecipe.recipe.image,
            modifier =
            Modifier
                .clip(RoundedCornerShape(20.dp))
                .height(128.dp)
                .fillMaxWidth(),
        )
        Text(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp),
            text = recipeRecommendItemUiState.recommendedRecipe.recipe.name,
        )
        Row(Modifier.padding(12.dp)) {
            Row(
                modifier =
                Modifier
                    .weight(1f)
                    .align(CenterVertically),
            ) {
                Image(
                    modifier =
                    Modifier
                        .align(CenterVertically)
                        .weight(1f),
                    painter = painterResource(R.drawable.ic_fridge),
                    contentDescription = "",
                )
                Text(
                    modifier =
                    Modifier
                        .align(CenterVertically)
                        .weight(1f),
                    color = Orange,
                    fontWeight = FontWeight.Bold,
                    text = "${recipeRecommendItemUiState.hadCount} / ${recipeRecommendItemUiState.hadCount + recipeRecommendItemUiState.needCount}",
                )
            }
            RecipeExtraInfoContainer(
                cookingTime = recipeRecommendItemUiState.recommendedRecipe.recipe.cookingTime,
                serving = recipeRecommendItemUiState.recommendedRecipe.recipe.servings,
                difficulty = recipeRecommendItemUiState.recommendedRecipe.recipe.difficulty,
                modifier =
                Modifier
                    .weight(2f)
                    .clip(RoundedCornerShape(32.dp))
                    .background(lightGray),
            )
        }
    }
}

@Composable
private fun RecipeExtraInfoContainer(
    difficulty: RecipeDifficulty,
    serving: Int,
    cookingTime: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        RecipeExtraInfo(
            difficulty = difficulty,
            serving = serving,
            cookingTime = cookingTime,
            paddingHorizontal = 12,
            fontSize = 10,
            starSize = 8,
        )
    }
}

@Composable
@Preview(showBackground = false)
fun PreviewRecipeItem() {
    BanchangoTheme {
        RecipeExtraInfoContainer(
            cookingTime = 10,
            serving = 2,
            difficulty = RecipeDifficulty.BEGINNER,
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun RecipeItem2Preview() {
    BanchangoTheme {
        RecipeItem(
            recipeRecommendItemUiState =
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
