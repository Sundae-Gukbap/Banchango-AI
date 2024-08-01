package com.sundaegukbap.banchango.feature.recipe.extrainfo

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
fun RecipeExtraInfo(
    difficulty: RecipeDifficulty,
    serving: Int,
    cookingTime: Int,
    paddingHorizontal: Int,
    fontSize: Int,
    starSize: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        RecipeDetailDifficulty(
            modifier =
                Modifier
                    .wrapContentSize()
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = paddingHorizontal.dp),
            difficulty = difficulty,
            textSize = fontSize,
            starSize = starSize,
        )

        RecipeLine(alignment = Alignment.CenterVertically)

        DetailText(
            modifier =
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
            text = "${serving}인분",
            paddingHorizontal = paddingHorizontal,
            fontSize = fontSize,
        )

        RecipeLine(alignment = Alignment.CenterVertically)

        DetailText(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "${cookingTime}m",
            paddingHorizontal = paddingHorizontal,
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
            modifier = Modifier.wrapContentSize(),
        )
        Text(
            text = difficulty.explain,
            fontSize = textSize.sp,
            fontWeight = Bold,
            color = Orange,
            modifier =
                Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
private fun RowScope.RecipeLine(alignment: Alignment.Vertical) {
    VerticalDivider(
        modifier =
            Modifier
                .height(52.dp)
                .align(alignment),
        color = LightOrange,
        thickness = 2.dp,
    )
}

@Composable
private fun RowScope.DetailText(
    paddingHorizontal: Int,
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
                .wrapContentSize()
                .padding(horizontal = paddingHorizontal.dp)
                .weight(1f),
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
            20,
            fontSize = 12,
            starSize = 20,
        )
    }
}
