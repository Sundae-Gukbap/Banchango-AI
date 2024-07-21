package com.sundaegukbap.banchango.feature.recipe.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sundaegukbap.banchango.Recipe
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.core.designsystem.theme.LightOrange
import com.sundaegukbap.banchango.core.designsystem.theme.Orange

@Composable
fun RecipeDetailCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            RecipeDetailInfo(
                difficulty = recipe.difficulty,
                serving = recipe.servings,
                cookingTime = recipe.cookingTime,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                paddingHorizontal = 32,
            )
        }
        item {
            Text(
                modifier = Modifier.padding(top = 20.dp, start = 12.dp, end = 12.dp),
                text = recipe.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        item {
            Text(
                text = recipe.introduction,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
            )
        }

        item {
            Ingredients(title = "핵심 재료", have = recipe.have, need = recipe.need)
        }
        item {
            Ingredients("식자재", recipe.have, recipe.need)
        }
    }
}

@Composable
private fun Ingredients(
    title: String,
    have: List<String>,
    need: List<String>,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 12.dp, end = 12.dp),
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "${have.size}/${have.size + need.size}",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier =
                Modifier
                    .background(color = Orange, shape = CircleShape)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterEnd),
        )
    }

    HorizontalDivider(
        modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
        color = Orange,
    )

    LazyRow(
        modifier = Modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        val ingredients =
            have.map { ItemIngredient(it, true) } + need.map { ItemIngredient(it, false) }
        itemsIndexed(ingredients) { index, it ->
            if (index == 0) {
                IngredientCard(it, modifier = Modifier.padding(start = 12.dp))
            } else {
                IngredientCard(it)
            }
        }
    }
}

@Composable
private fun IngredientCard(
    itemIngredient: ItemIngredient,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .border(1.dp, color = Orange, shape = CircleShape)
                .background(
                    color = if (itemIngredient.has) Color.White else LightOrange,
                    shape = CircleShape,
                ),
    ) {
        Text(modifier = Modifier.padding(16.dp), text = itemIngredient.name)
    }
}

data class ItemIngredient(
    val name: String,
    val has: Boolean,
)

@Preview(showBackground = true)
@Composable
fun RecipeDetailCardPreview() {
    RecipeDetailCard(
        recipe =
            Recipe(
                id = 1,
                name = "간장계란볶음밥",
                introduction =
                    "아주 간단하면서 맛있는 계란간장볶음밥으로 한끼식사 만들어보세요. \n " +
                        "아이들이 더 좋아할거예요. \n",
                image = "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg",
                link = "https://www.10000recipe.com/recipe/6889616",
                cookingTime = 10,
                servings = 2,
                difficulty = RecipeDifficulty.BEGINNER,
                have = listOf("계란", "간장"),
                need = listOf("식초", "당근", "감자", "깨소금", "밥"),
            ),
    )
}
