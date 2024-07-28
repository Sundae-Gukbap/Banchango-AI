package com.sundaegukbap.banchango.feature.recipe.recommend

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme
import kotlin.math.absoluteValue

@Composable
fun RecipeRecommendRoute(
    padding: PaddingValues,
    viewModel: RecipeRecommendViewModel = hiltViewModel(),
    onRecipeClick: (Recipe) -> Unit,
    onChangeStatusBarColor: (color: Color, darkIcons: Boolean) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (uiState is RecipeRecommendUiState.Loading)
            viewModel.getRecipeRecommendation()
    }

    RecipeRecommendContent(
        padding = padding,
        uiState = uiState,
        onLikeClick = { viewModel.likeRecipe(it) },
        onLastPageVisible = { viewModel.getRecipeRecommendation() },
        onRecipeClick = onRecipeClick,
        onChangeSystemBarsColor = onChangeStatusBarColor
    )
}

@Composable
fun RecipeRecommendContent(
    padding: PaddingValues,
    uiState: RecipeRecommendUiState,
    onLikeClick: (Recipe) -> Unit,
    onLastPageVisible: () -> Unit,
    onRecipeClick: (Recipe) -> Unit,
    onChangeSystemBarsColor: (color: Color, darkIcons: Boolean) -> Unit,
) {
    when (uiState) {
        is RecipeRecommendUiState.Loading -> {
            RecipeRecommendLoading(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            )
        }

        is RecipeRecommendUiState.Success -> {
            RecipeRecommendScreen(
                recipeRecommends = uiState.recipes,
                padding = padding,
                onLikeClick = onLikeClick,
                onLastPageVisible = onLastPageVisible,
                onRecipeClick = onRecipeClick
            )
        }
    }
}

@Composable
fun RecipeRecommendLoading(modifier: Modifier, contentAlignment: Alignment) {
    Box(modifier = modifier, contentAlignment = contentAlignment) {
        CircularProgressIndicator()
    }
}

@Composable
private fun RecipeRecommendScreen(
    padding: PaddingValues,
    recipeRecommends: List<RecipeRecommendItemUiState>,
    onLikeClick: (Recipe) -> Unit,
    onRecipeClick: (Recipe) -> Unit,
    onLastPageVisible: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { recipeRecommends.size + 1 })

    Box(modifier = Modifier.fillMaxSize()) {
        VerticalPager(
            modifier = Modifier.padding(padding),
            state = pagerState,
            contentPadding = PaddingValues(vertical = 136.dp, horizontal = 32.dp),
            pageSpacing = 24.dp,
        ) { page ->
            if (page >= recipeRecommends.size - 2) {
                onLastPageVisible()
            }
            val pageOffset =
                (pagerState.currentPage - page + pagerState.currentPageOffsetFraction).absoluteValue

            if (page == pagerState.pageCount - 1) {
                RecipeRecommendLoading(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
            } else {
                RecipeItem(
                    recipeItemUiState = recipeRecommends[page],
                    onRecipeClick = onRecipeClick,
                    onRecipeLikeClick = onLikeClick,
                    pageOffset = pageOffset,
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun RecipePagePreview() {
    BanchangoTheme {
        RecipeRecommendContent(
            padding = PaddingValues(16.dp),
            onLikeClick = {},
            onLastPageVisible = {},
            onRecipeClick = {},
            onChangeSystemBarsColor = { _, _ -> },
            uiState = RecipeRecommendUiState.Success(
                listOf(
                    RecipeRecommendItemUiState(
                        recipe = Recipe(
                            id = 1,
                            name = "간장계란볶음밥",
                            introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                            image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                            link = "https://www.10000recipe.com/recipe/6889616",
                            cookingTime = 10,
                            servings = 2,
                            difficulty = RecipeDifficulty.BEGINNER,
                            have = listOf(""),
                            need = listOf(""),
                        ),
                        isLiked = false
                    ),
                    RecipeRecommendItemUiState(
                        recipe = Recipe(
                            id = 1,
                            name = "간장계란볶음밥",
                            introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                            image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                            link = "https://www.10000recipe.com/recipe/6889616",
                            cookingTime = 10,
                            servings = 2,
                            difficulty = RecipeDifficulty.BEGINNER,
                            have = listOf(""),
                            need = listOf(""),
                        ),
                        isLiked = false
                    ),
                    RecipeRecommendItemUiState(
                        recipe = Recipe(
                            id = 1,
                            name = "간장계란볶음밥",
                            introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                            image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                            link = "https://www.10000recipe.com/recipe/6889616",
                            cookingTime = 10,
                            servings = 2,
                            difficulty = RecipeDifficulty.BEGINNER,
                            have = listOf(""),
                            need = listOf(""),
                        ),
                        isLiked = false
                    ),
                )
            )
        )
    }
}
