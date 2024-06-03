package com.sundaegukbap.banchango.feature.reciperecommend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.core.designsystem.component.NetworkImage
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme

@Composable
fun RecipeCard(
    page: Int,
    recipe: Recipe,
    onHateClick: (page: Int) -> Unit = {},
    onLikeClick: (page: Int) -> Unit = {},
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .graphicsLayer { }) {
        NetworkImage(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(32.dp))
                .fillMaxSize(),
            url = recipe.image,
        )
        RecipeInfo(
            modifier = Modifier
                .fillMaxSize(),
            recipe, page, onHateClick, onLikeClick
        )
    }
}

@Composable
private fun RecipeInfo(
    modifier: Modifier,
    recipe: Recipe,
    page: Int,
    onHateClick: (page: Int) -> Unit,
    onLikeClick: (page: Int) -> Unit
) {
    Box(modifier = modifier) {
        Text(
            text = recipe.name,
            color = Color.White,
            fontSize = 16.sp,
            style = TextStyle(fontWeight = Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .clip(shape = RoundedCornerShape(32.dp))
                .background(Color.Black.copy(alpha = 0.5f))
                .fillMaxWidth()
                .padding(16.dp),
        )

        RecipeLikeHate(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            recipe = recipe,
            page = page,
            onHateClick = onHateClick,
            onLikeClick = onLikeClick
        )
    }
}

@Composable
private fun RecipeLikeHate(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    page: Int,
    onHateClick: (page: Int) -> Unit,
    onLikeClick: (page: Int) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "${recipe.have.count()} / ${recipe.need.count() + recipe.have.count()}",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
        )
        Button(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 12.dp),
            onClick = {
                onHateClick(page)
            }
        ) {
            Text("싫어요")
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 12.dp),
            onClick = {
                onLikeClick(page + 1)
            },
        ) {
            Text("좋아요")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    BanchangoTheme {
        RecipeCard(
            page = 1, recipe = Recipe(
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
            )
        )
    }
}
