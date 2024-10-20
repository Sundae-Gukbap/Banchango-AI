package com.sundaegukbap.banchango.feature.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sundaegukbap.banchango.core.designsystem.theme.LightOrange
import com.sundaegukbap.banchango.feature.home.R

@Composable
fun AddButton(
    containerColor: Color,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        colors = ButtonDefaults.elevatedButtonColors(containerColor = containerColor),
        onClick = onAddClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun AddContainerButtonPreview() {
    AddButton(
        containerColor = LightOrange,
        onAddClick = {},
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    )
}