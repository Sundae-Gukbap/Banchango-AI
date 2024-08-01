package com.sundaegukbap.banchango.feature.recipe.recommend

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.RecommendedRecipe
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme
import com.sundaegukbap.banchango.feature.reciperecommend.R

@Composable
fun RecipeRecommendRoute(
    padding: PaddingValues,
    viewModel: RecipeRecommendViewModel = hiltViewModel(),
    onRecipeClick: (Recipe) -> Unit,
    onChangeStatusBarColor: (color: Color, darkIcons: Boolean) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (uiState is RecipeRecommendUiState.Loading) {
            viewModel.getRecipeRecommendation()
        }
    }

    RecipeRecommendContent(
        padding = padding,
        uiState = uiState,
        onRefreshClick = { viewModel.getRecipeRecommendation() },
        onLikeClick = { viewModel.likeRecipe(it) },
        onRecipeClick = onRecipeClick,
        onChangeSystemBarsColor = onChangeStatusBarColor,
    )
}

@Composable
fun RecipeRecommendContent(
    padding: PaddingValues,
    uiState: RecipeRecommendUiState,
    onRefreshClick: () -> Unit,
    onLikeClick: (Recipe) -> Unit,
    onRecipeClick: (Recipe) -> Unit,
    onChangeSystemBarsColor: (color: Color, darkIcons: Boolean) -> Unit,
) {
    onChangeSystemBarsColor(Color.White, true)
    when (uiState) {
        is RecipeRecommendUiState.Loading -> {
            RecipeRecommendLoading(
                padding = padding,
                contentAlignment = Alignment.Center,
                onRefreshClick = {},
            )
        }

        is RecipeRecommendUiState.Success -> {
            RecipeRecommendScreen(
                recipeRecommends = uiState.recipes,
                padding = padding,
                onLikeClick = onLikeClick,
                onRecipeClick = onRecipeClick,
                onRefreshClick = onRefreshClick,
            )
        }
    }
}

@Composable
fun RecipeRecommendLoading(
    padding: PaddingValues,
    contentAlignment: Alignment,
    onRefreshClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = contentAlignment
    ) {
        RecipeRecommendTopBar(onRefreshClick, Modifier.align(Alignment.TopCenter))
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun RecipeRecommendScreen(
    padding: PaddingValues,
    recipeRecommends: List<RecipeRecommendItemUiState>,
    onRefreshClick: () -> Unit,
    onLikeClick: (Recipe) -> Unit,
    onRecipeClick: (Recipe) -> Unit,
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        contentPadding = padding,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        item {
            RecipeRecommendTopBar(onRefreshClick = onRefreshClick)
        }
        items(
            items = recipeRecommends,
            key = { it.recommendedRecipe.recipe.id }
        ) { item ->
            RecipeItem(item, onRecipeClick, onLikeClick)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun RecipeRecommendTopBar(onRefreshClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Text(text = "AI 레시피 추천", fontWeight = FontWeight.Bold)
        Image(
            painterResource(id = R.drawable.ic_refresh),
            contentDescription = "refresh",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable { onRefreshClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipePagePreview() {
    BanchangoTheme {
        RecipeRecommendContent(
            padding = PaddingValues(16.dp),
            onLikeClick = {},
            onRecipeClick = {},
            onRefreshClick = {},
            onChangeSystemBarsColor = { _, _ -> },
            uiState =
            RecipeRecommendUiState.Success(
                listOf(
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
                            hadIngredients = listOf(),
                            neededIngredients = listOf(),
                        ),
                        isLiked = false,
                    ),
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
                            hadIngredients = listOf(),
                            neededIngredients = listOf(),
                        ),
                        isLiked = false,
                    ),
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
                            hadIngredients = listOf(),
                            neededIngredients = listOf(),
                        ),
                        isLiked = false,
                    ),
                ),
            ),
        )
    }
}
