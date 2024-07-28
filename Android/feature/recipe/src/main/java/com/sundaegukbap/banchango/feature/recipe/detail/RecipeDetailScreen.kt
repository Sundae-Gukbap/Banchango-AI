package com.sundaegukbap.banchango.feature.recipe.detail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sundaegukbap.banchango.LikableRecipe
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.core.designsystem.component.NetworkImage

@Composable
fun RecipeDetailRoute(
    recipeId: Long,
    modifier: Modifier = Modifier,
    onChangeSystemBarsColor: (color: Color, darkIcons: Boolean) -> Unit,
    viewModel: RecipeDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getRecipeDetail(recipeId)
    }

    RecipeDetailContent(
        uiState = uiState,
        onChangeSystemBarsColor = onChangeSystemBarsColor,
        modifier = modifier,
    ) { likableRecipe ->
        viewModel.likeRecipe(likableRecipe.recipe.id, !likableRecipe.isLiked)
    }
}

@Composable
fun RecipeDetailContent(
    modifier: Modifier = Modifier,
    uiState: RecipeDetailUiState,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit = {},
    onChangeSystemBarsColor: (color: Color, darkIcons: Boolean) -> Unit,
    onLikeCLick: (likableRecipe: LikableRecipe) -> Unit,
) {
    when (uiState) {
        is RecipeDetailUiState.Loading -> {
            RecipeDetailLoading()
        }

        is RecipeDetailUiState.Success -> {
            onChangeSystemBarsColor(Color.Transparent, false)
            RecipeDetailScreen(
                likableRecipe = uiState.likableRecipe,
                modifier = modifier,
                onLikeCLick = { onLikeCLick(uiState.likableRecipe) },
                onChangeSystemBarsColor = onChangeSystemBarsColor,
            )
        }

        is RecipeDetailUiState.Error -> {
            onShowErrorSnackBar(uiState.throwable)
        }
    }
}

@Composable
private fun RecipeDetailLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecipeDetailScreen(
    likableRecipe: LikableRecipe,
    modifier: Modifier = Modifier,
    onChangeSystemBarsColor: (color: Color, darkIcons: Boolean) -> Unit,
    onLikeCLick: () -> Unit,
) {
    val scaffoldState =
        rememberBottomSheetScaffoldState(
            rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded),
        )

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var topBarIconColor by remember { mutableStateOf(Color.White) } // 초기 아이콘 색상 설정
    var topBarBackgroundColor by remember { mutableStateOf(Color.Transparent) }

    LaunchedEffect(scaffoldState.bottomSheetState) {
        snapshotFlow { scaffoldState.bottomSheetState.requireOffset() }
            .collect { offsetValue ->
                if (offsetValue.dp >= (screenHeight * 0.4f)) {
                    topBarBackgroundColor = Color.Transparent
                    topBarIconColor = Color.White
                    onChangeSystemBarsColor(Color.Transparent, false)
                } else {
                    topBarBackgroundColor = Color.White
                    topBarIconColor = Color.Gray
                    onChangeSystemBarsColor(Color.White, true)
                }
            }
    }

    Box(
        modifier =
        modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .fillMaxSize(),
    ) {
        RecipeImage(
            imageUrl = likableRecipe.recipe.image,
            modifier =
            Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.5f)
                .align(Alignment.TopCenter),
        )
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                RecipeDetailCard(
                    likableRecipe.recipe,
                    Modifier.height(screenHeight * 0.98f),
                )
            },
            sheetContainerColor = Color.White,
            sheetPeekHeight = screenHeight * 0.7f,
        ) {}

        RecipeTobBar(
            backgroundColor = topBarBackgroundColor,
            navigationIconColor = topBarIconColor,
            actionIconColor = topBarIconColor,
            onBackClick = { onBackPressedDispatcher?.onBackPressed() },
            onLikeClick = onLikeCLick,
        )

        BtnMoveToRecipe(
            likableRecipe.recipe.link,
            modifier =
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
        )
    }
}

@Composable
private fun RecipeImage(
    imageUrl: String,
    modifier: Modifier,
) {
    Box(modifier = modifier) {
        NetworkImage(
            url = imageUrl,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush =
                    Brush.verticalGradient(
                        colors =
                        listOf(
                            Color.Black.copy(alpha = 0.3f),
                            Color.Transparent,
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY,
                    ),
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    RecipeDetailScreen(
        likableRecipe =
            LikableRecipe(
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
                        have = listOf("계란", "간장"),
                        need = listOf("식초", "당근", "감자"),
                    ),
                isLiked = false,
            ),
        onLikeCLick = {},
        onChangeSystemBarsColor = { color, darkIcons -> },
    )
}
