package com.sundaegukbap.banchango.feature.reciperecommend

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecipeRecommendRoute(
    padding: PaddingValues,
    viewModel: RecipeRecommendViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getRecipeRecommendation()
    }

    RecipeRecommendContent(
        padding = padding,
        uiState = uiState,
        onHateClick = viewModel::hateRecipe,
        onLastPageVisible = viewModel::getRecipeRecommendation,
    )
}

@Composable
fun RecipeRecommendContent(
    padding: PaddingValues,
    uiState: RecipeRecommendUiState,
    onHateClick: (Int) -> Unit = {},
    onLikeClick: (Int) -> Unit = {},
    onLastPageVisible: () -> Unit = {},
) {

    when (uiState) {
        is RecipeRecommendUiState.Loading -> {
            RecipeRecommendLoading()
        }

        is RecipeRecommendUiState.Success -> {
            RecipeRecommendScreen(
                recipeRecommends = uiState.recipes,
                padding = padding,
                onHateClick = onHateClick,
                onLikeClick = onLikeClick,
                onLastPageVisible = onLastPageVisible,
            )
        }
    }
}


@Composable
fun RecipeRecommendLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun RecipeRecommendScreen(
    padding: PaddingValues,
    recipeRecommends: List<RecipeRecommendItemUiState>,
    onHateClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
    onLastPageVisible: () -> Unit = {},
) {
    val pagerState = rememberPagerState(pageCount = { recipeRecommends.size })
    val coroutineScope = rememberCoroutineScope()

    VerticalPager(
        modifier = Modifier.padding(padding),
        state = pagerState,
        contentPadding = PaddingValues(vertical = 136.dp, horizontal = 32.dp),
        pageSpacing = 24.dp,
    ) { page ->

        var visible by remember { mutableStateOf(true) }

        if (page >= recipeRecommends.size - 2) {
            onLastPageVisible()
        }

        RecipePage(
            visible = visible,
            page = page,
            recipeRecommends = recipeRecommends,
            onLikeClick = {
                coroutineScope.launch {
                    onLikeClick(it)
                    pagerState.animateScrollToPage(it + 1)
                }
            },
            onHateClick = { visible = false },
        )

        LaunchedEffect(visible) {
            if (!visible) {
                delay(300)
                coroutineScope.launch {
                    val currentPage = pagerState.currentPage
                    when {
                        currentPage + 1 < recipeRecommends.size && currentPage > 0 -> {
                            visible = true
                            pagerState.animateScrollToPage(page + 1)
                        }

                        currentPage + 1 < recipeRecommends.size -> {
                            pagerState.animateScrollToPage(page + 1)
                        }

                        currentPage + 1 == recipeRecommends.size -> {
                            pagerState.animateScrollToPage(page - 1)
                        }
                    }

                    delay(300)
                    onHateClick(page)
                    visible = true

                    when {
                        currentPage < recipeRecommends.size -> {
                            pagerState.scrollToPage(currentPage)
                        }

                        recipeRecommends.isNotEmpty() -> {
                            pagerState.scrollToPage(recipeRecommends.size - 1)
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun RecipePage(
    visible: Boolean,
    page: Int,
    recipeRecommends: List<RecipeRecommendItemUiState>,
    onLikeClick: (Int) -> Unit,
    onHateClick: (Int) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = EnterTransition.None,
        exit = fadeOut(),
    ) {
        RecipeCard(
            page = page,
            recipe = recipeRecommends[page].recipe,
            onLikeClick = onLikeClick,
            onHateClick = onHateClick,
        )
    }
}
