package com.sundaegukbap.banchango.feature.reciperecommend

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
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

@Composable
fun RecipesRecommendScreen(
    padding: PaddingValues,
    viewModel: RecipeRecommendViewModel = hiltViewModel(),
) {
    val recipesUiState by viewModel.recipes.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(pageCount = { recipesUiState.size })
    val coroutineScope = rememberCoroutineScope()

    viewModel.getRecipeRecommendation()

    VerticalPager(
        modifier = Modifier.padding(padding),
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
