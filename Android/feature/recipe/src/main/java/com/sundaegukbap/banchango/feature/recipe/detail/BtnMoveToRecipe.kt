package com.sundaegukbap.banchango.feature.recipe.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.sundaegukbap.banchango.core.designsystem.theme.LightOrange
import com.sundaegukbap.banchango.core.designsystem.theme.Orange

@Composable
fun BtnMoveToRecipe(
    recipeLink: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Button(
        modifier = modifier,
        colors =
            ButtonColors(
                containerColor = Orange,
                contentColor = Color.White,
                disabledContainerColor = LightOrange,
                disabledContentColor = Color.White,
            ),
        onClick = {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(recipeLink)),
            )
        },
    ) {
        Text(text = "레시피 이동하기")
    }
}

@Composable
@Preview
fun PreviewBtnMoveToRecipe() {
    BtnMoveToRecipe(recipeLink = "https://www.google.com")
}
