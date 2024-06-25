package com.sundaegukbap.banchango.feature.recipe.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sundaegukbap.banchango.RecipeDifficulty
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme
import com.sundaegukbap.banchango.core.designsystem.theme.LightOrange
import com.sundaegukbap.banchango.core.designsystem.theme.Orange
import com.sundaegukbap.banchango.feature.recipe.difficulty.Stars

@Composable
fun RecipeDetailInfo(
    difficulty: RecipeDifficulty,
    serving: Int,
    cookingTime: Int,
    paddingHorizontal: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        RecipeDetailDifficulty(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically)
                .padding(horizontal = paddingHorizontal.dp),
            difficulty = difficulty
        )

        RecipeLine(alignment = Alignment.CenterVertically)

        DetailText(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "${serving}인분",
            paddingHorizontal = paddingHorizontal
        )

        RecipeLine(alignment = Alignment.CenterVertically)

        DetailText(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "${cookingTime}m",
            paddingHorizontal = paddingHorizontal
        )
    }
}

@Composable
private fun RecipeDetailDifficulty(
    modifier: Modifier = Modifier,
    difficulty: RecipeDifficulty
) {
    Column(modifier = modifier) {
        Stars(
            modifier = Modifier.wrapContentSize(),
            starSize = 14,
            filledCount = difficulty.level
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            text = difficulty.explain,
            fontSize = 12.sp,
            fontWeight = Bold,
            color = Orange,
        )
    }
}

@Composable
private fun RowScope.RecipeLine(alignment: Alignment.Vertical) {
    VerticalDivider(
        modifier = Modifier
            .height(52.dp)
            .align(alignment),
        color = LightOrange,
        thickness = 2.dp
    )
}

@Composable
private fun RowScope.DetailText(
    modifier: Modifier = Modifier,
    paddingHorizontal: Int,
    text: String
) {
    Text(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = paddingHorizontal.dp)
            .align(Alignment.CenterVertically),
        text = text,
        fontSize = 12.sp,
        fontWeight = Bold,
        color = Orange,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailInfoPreview() {
    BanchangoTheme {
        RecipeDetailInfo(
            difficulty = RecipeDifficulty.ADVANCED,
            serving = 2,
            cookingTime = 30,
            20,
        )
    }
}

