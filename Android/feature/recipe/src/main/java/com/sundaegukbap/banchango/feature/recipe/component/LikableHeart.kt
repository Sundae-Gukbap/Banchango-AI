package com.sundaegukbap.banchango.feature.recipe.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.sundaegukbap.banchango.feature.reciperecommend.R

@Composable
fun LikableHeart(isLike: Boolean, onLikeClick: () -> Unit, modifier: Modifier = Modifier) {
    val id = if (isLike) {
        R.drawable.ic_heart_filled
    } else {
        R.drawable.ic_heart
    }
    Image(painterResource(id = id), contentDescription = null,
        modifier
            .clickable {
                onLikeClick()
            })
}
