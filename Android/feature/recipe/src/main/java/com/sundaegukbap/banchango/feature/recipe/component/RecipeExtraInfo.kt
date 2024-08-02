package com.sundaegukbap.banchango.feature.recipe.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
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

@Composable
fun RecipeExtraInfo(
    difficulty: RecipeDifficulty,
    serving: Int,
    cookingTime: Int,
    barHeight: Int,
    fontSize: Int,
    starSize: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        RecipeDetailDifficulty(
            modifier =
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            difficulty = difficulty,
            textSize = fontSize,
            starSize = starSize,
        )

        RecipeLine(height = barHeight, alignment = Alignment.CenterVertically)

        DetailText(
            modifier =
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = "${serving}인분",
            fontSize = fontSize,
        )

        RecipeLine(height = barHeight, alignment = Alignment.CenterVertically)

        DetailText(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "${cookingTime}m",
            fontSize = fontSize,
        )
    }
}

@Composable
private fun RecipeDetailDifficulty(
    difficulty: RecipeDifficulty,
    textSize: Int,
    starSize: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Stars(
            starSize = starSize,
            filledCount = difficulty.level,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
        )
        Text(
            text = difficulty.explain,
            fontSize = textSize.sp,
            fontWeight = Bold,
            color = Orange,
            modifier =
            Modifier
                .align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
private fun RowScope.RecipeLine(height: Int, alignment: Alignment.Vertical) {
    VerticalDivider(
        modifier =
        Modifier
            .height(height.dp)
            .align(alignment),
        color = LightOrange,
        thickness = 2.dp,
    )
}

@Composable
private fun RowScope.DetailText(
    text: String,
    fontSize: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontWeight = Bold,
        color = Orange,
        textAlign = TextAlign.Center,
        modifier =
        modifier
            .weight(1f)
            .fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailInfoPreview() {
    BanchangoTheme {
        RecipeExtraInfo(
            difficulty = RecipeDifficulty.ADVANCED,
            serving = 2,
            cookingTime = 30,
            fontSize = 12,
            starSize = 20,
            barHeight = 56,
        )
    }
}
