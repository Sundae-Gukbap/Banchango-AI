package com.sundaegukbap.banchango.feature.reciperecommend

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecipesRecommendScreen(
    padding: PaddingValues,
    viewModel: RecipeRecommendViewModel = hiltViewModel(),
) {
    val recipesUiState by viewModel.recipes.collectAsStateWithLifecycle()
    var items by remember { mutableStateOf(recipesUiState) }
    val pagerState = rememberPagerState(pageCount = { items.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getRecipeRecommendation()
    }

    VerticalPager(
        modifier = Modifier.padding(padding),
        state = pagerState,
        contentPadding = PaddingValues(vertical = 136.dp, horizontal = 32.dp),
        pageSpacing = 24.dp,
    ) { page ->

        if (page < items.size) {
            var visible by remember { mutableStateOf(true) }

            LaunchedEffect(visible) {
                if (!visible) {
                    delay(300)
                    coroutineScope.launch {
                        val currentPage = pagerState.currentPage
                        val nextPage = currentPage + 1
                        if (nextPage < items.size) {
                            if (currentPage > 0) {
                                visible = true
                            }
                            pagerState.animateScrollToPage(page + 1)
                        }
                        viewModel.hateRecipe(page)
                        items = items.toMutableList().apply { removeAt(page) }
                        if (currentPage < items.size) {
                            pagerState.scrollToPage(currentPage)
                        } else if (items.isNotEmpty()) {
                            pagerState.scrollToPage(items.size - 1) // 마지막 페이지로 이동
                        }
                        visible = true
                    }
                }
            }

            AnimatedVisibility(
                visible = visible,
                enter = EnterTransition.None,
                exit = fadeOut(),
            ) {
                RecipeCard(
                    page = page,
                    recipe = items[page],
                    onLikeClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(it)
                        }
                    },
                    onHateClick = {
                        visible = false
                    },
                )
            }
        }
    }
}
