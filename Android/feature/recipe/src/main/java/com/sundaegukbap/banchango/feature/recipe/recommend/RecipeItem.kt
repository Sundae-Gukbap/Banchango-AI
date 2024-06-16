package com.sundaegukbap.banchango.feature.recipe.recommend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.core.designsystem.component.NetworkImage
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme
import com.sundaegukbap.banchango.feature.reciperecommend.R

@Composable
fun RecipeItem(
    recipeItemUiState: RecipeRecommendItemUiState,
    pageOffset: Float,
    onRecipeClick: (Recipe) -> Unit = {},
    onRecipeLikeClick: (Recipe) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = lerp(
                    start = 0.9f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
                scaleY = lerp(
                    start = 0.9f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
            .clip(shape = RoundedCornerShape(44.dp))
            .clickable {
                onRecipeClick(recipeItemUiState.recipe)
            }
    ) {
        NetworkImage(
            modifier = Modifier
                .fillMaxSize(),
            url = recipeItemUiState.recipe.image,
        )
        RecipeInfo(
            recipeItemUiState.recipe,
            modifier = Modifier.fillMaxSize(),
        )

        RecipeIngredientCount(
            modifier = Modifier
                .align(Alignment.BottomStart),
            have = recipeItemUiState.recipe.have.count(),
            need = recipeItemUiState.recipe.need.count(),
            imageSize = 95,
        )

        RecipeLikeButton(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            isLiked = recipeItemUiState.isLiked,
            onLikeClick = { onRecipeLikeClick(recipeItemUiState.recipe) },
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(
                        alpha = lerp(
                            start = 0.5f,
                            stop = 0f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    ),
                )
        )
    }
}

@Composable
private fun RecipeInfo(
    recipe: Recipe,
    modifier: Modifier,
) {
    Box(modifier = modifier) {
        Text(
            text = recipe.name,
            color = Color.White,
            fontSize = 24.sp,
            maxLines = 2,
            minLines = 2,
            style = TextStyle(fontWeight = Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(listOf(Color.Black, Color.Transparent)),
                    alpha = 0.8f
                )
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 40.dp),
        )
    }
}

@Composable
fun RecipeLikeButton(
    modifier: Modifier,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onLikeClick
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_btn_right),
            contentDescription = null,
            modifier = Modifier.size(95.dp),
        )
        Image(
            painter = painterResource(id = if (isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.Center)
                .padding(top = 8.dp),
        )
    }
}

@Preview(showBackground = false)
@Composable
fun RecipeItemPreview() {
    BanchangoTheme {
        RecipeItem(
            recipeItemUiState = RecipeRecommendItemUiState(
                recipe = Recipe(
                    id = 1,
                    name = "간장계란볶음밥",
                    introduction = "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. 아이들이 더 좋아할거예요.",
                    image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                    link = "https://www.10000recipe.com/recipe/6889616",
                    cookingTime = 10,
                    servings = 2,
                    difficulty = "Easy",
                    have = listOf(""),
                    need = listOf(""),
                    isBookmarked = false,
                ),
                isLiked = true,
            ),
            pageOffset = 0f,
        )
    }
}

@Composable
fun RecipeIngredientCount(
    modifier: Modifier,
    imageSize: Int,
    have: Int,
    need: Int,
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.size(imageSize.dp),
            painter = painterResource(id = R.drawable.img_wave),
            contentDescription = null,
        )
        Text(
            text = "$have / ${need + have}",
            modifier = Modifier
                .padding(top = 32.dp)
                .align(Alignment.Center),
            color = Color.White,
            style = TextStyle(
                letterSpacing = 0.1.sp,
                fontWeight = Bold,
                fontSize = 20.sp
            ),
        )
    }
}

@Preview(showBackground = false)
@Composable
fun RecipeIngredientCountPreview() {
    BanchangoTheme {
        RecipeIngredientCount(modifier = Modifier, have = 3, need = 7, imageSize = 120)
    }
}
