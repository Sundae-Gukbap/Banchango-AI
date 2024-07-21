package com.sundaegukbap.banchango.feature.recipe.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sundaegukbap.banchango.feature.reciperecommend.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeTobBar(
    backgroundColor: Color,
    navigationIconColor: Color,
    actionIconColor: Color,
    onBackClick: () -> Unit,
    onLikeClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.background(Color.Transparent),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = onLikeClick, modifier = Modifier) {
                Icon(
                    ImageVector.vectorResource(R.drawable.ic_heart_filled),
                    contentDescription = "Like",
                    modifier = Modifier.size(20.dp),
                )
            }
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor,
                scrolledContainerColor = backgroundColor,
                navigationIconContentColor = navigationIconColor,
                actionIconContentColor = actionIconColor,
            ),
    )
}

@Preview(showBackground = false)
@Composable
fun PreviewRecipeTopBar() {
    RecipeTobBar(
        backgroundColor = Color.White,
        onBackClick = {},
        onLikeClick = {},
        navigationIconColor = Color.Black,
        actionIconColor = Color.Black,
    )
}
