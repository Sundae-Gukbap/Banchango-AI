package com.sundaegukbap.banchango.feature.recipe.difficulty

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme
import com.sundaegukbap.banchango.feature.reciperecommend.R

@Composable
fun Stars(
    modifier: Modifier = Modifier,
    starSize: Int,
    filledCount: Int,
) {
    Row(modifier = modifier) {
        repeat(filledCount) {
            Star(modifier = Modifier.size(starSize.dp), isFilled = true)
        }
        repeat(5 - filledCount) {
            Star(modifier = Modifier.size(starSize.dp), isFilled = false)
        }
    }
}

@Composable
fun Star(modifier: Modifier, isFilled: Boolean) {
    Image(
        modifier = modifier,
        painter = painterResource(id = if (isFilled) R.drawable.ic_star_filled else R.drawable.ic_star_outline),
        contentDescription = null,
    )
}

@Preview(showBackground = true)
@Composable
fun StarPreview() {
    BanchangoTheme {
        Row {
            Star(modifier = Modifier.size(40.dp), isFilled = true)
            Star(modifier = Modifier.size(40.dp), isFilled = false)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DifficultyStarPreview() {
    BanchangoTheme {
        Stars(modifier = Modifier.wrapContentSize(), starSize = 24, filledCount = 3)
    }
}
