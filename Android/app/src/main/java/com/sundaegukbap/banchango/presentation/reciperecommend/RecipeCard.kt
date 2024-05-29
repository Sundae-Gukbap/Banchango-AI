package com.sundaegukbap.banchango.presentation.reciperecommend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sundaegukbap.banchango.core.designsystem.NetworkImage
import com.sundaegukbap.banchango.model.Recipe
import com.sundaegukbap.banchango.ui.theme.BanchangoTheme

@Composable
fun RecipeCard(
    page: Int,
    recipe: Recipe,
    onHateClick: (page: Int) -> Unit = {},
    onLikeClick: (page: Int) -> Unit = {},
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
        RecipeInfo(recipe, page, onHateClick, onLikeClick)
    }
}

@Composable
private fun RecipeInfo(
    recipe: Recipe,
    page: Int,
    onHateClick: (page: Int) -> Unit,
    onLikeClick: (page: Int) -> Unit
) {
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
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Button(
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
                        onHateClick(page + 1)
                    }
                ) {
                    Text("싫어요")
                }
                Button(
                    onClick = {
                        onLikeClick(page + 1)
                    },
                ) {
                    Text("좋아요")
                }
            }
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
                have = listOf(1, 2, 3, 4, 5),
                need = listOf(6, 7, 8, 9, 10),
            )
        )
    }
}
