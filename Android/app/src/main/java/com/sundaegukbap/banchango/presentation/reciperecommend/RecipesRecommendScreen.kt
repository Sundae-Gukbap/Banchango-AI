package com.sundaegukbap.banchango.presentation.reciperecommend

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipesRecommendScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipeRecommendViewModel = hiltViewModel(),
) {
    val recipesUiState by viewModel.recipes.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(
        pageCount = {
            recipesUiState.size
        }
    )
    val coroutineScope = rememberCoroutineScope()
    VerticalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(vertical = 200.dp, horizontal = 40.dp),
        pageSpacing = 40.dp,
    ) { page ->
        RecipeCard(
            page = page,
            recipe = recipesUiState[page],
            onLikeClick = {
                coroutineScope.launch { pagerState.animateScrollToPage(it) }
            },
            onHateClick = {
                coroutineScope.launch { pagerState.animateScrollToPage(it) }
            },
        )
    }
}
