package com.sundaegukbap.banchango.core.designsystem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NetworkImage(modifier: Modifier, url: String) {
    GlideImage(
        model = url,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        loading = placeholder(ColorPainter(Color(0xD9FFFFFF))),
        failure = placeholder(ColorPainter(Color(0xD9FFFFFF))),
    )
}

