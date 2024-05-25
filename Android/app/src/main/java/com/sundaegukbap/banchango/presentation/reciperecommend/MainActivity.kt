package com.sundaegukbap.banchango.presentation.reciperecommend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sundaegukbap.banchango.ui.theme.BanchangoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RecipeRecommendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.getRecipeRecommendation()
        setContent {
            BanchangoTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color(0xBFFFFFFF), darkIcons = true)
                systemUiController.setNavigationBarColor(
                    color = Color(0xFFFFFFFF)
                )
                RecipesRecommendScreen()
            }
        }
    }
}

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
    VerticalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(vertical = 200.dp, horizontal = 40.dp),
        pageSpacing = 40.dp,
    ) { page ->
        RecipeScreen(
            pagerState = pagerState,
            page = page,
            recipesUiState[page],
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RecipeScreen(
    pagerState: PagerState,
    page: Int,
    recipe: Recipe,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        NetworkImage(
            modifier = Modifier
                .fillMaxSize(),
            url = recipe.image,
        )
        Box {
            Column {
                Text(
                    recipe.name,
                    color = Color.White,
                    fontSize = 24.sp,
                    style = TextStyle(fontWeight = Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    text = page.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    fontSize = 60.sp
                )
                val coroutineScope = rememberCoroutineScope()
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Button(
                        modifier = Modifier.padding(end = 16.dp),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page + 1)
                            }
                        }
                    ) {
                        Text("싫어요")
                    }
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page + 1)
                            }
                        },
                    ) {
                        Text("좋아요")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BanchangoTheme {
        RecipesRecommendScreen()
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NetworkImage(modifier: Modifier, url: String) {
    GlideImage(
        model = url,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
    )
}
