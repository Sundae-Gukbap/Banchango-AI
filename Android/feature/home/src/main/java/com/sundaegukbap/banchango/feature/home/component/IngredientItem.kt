package com.sundaegukbap.banchango.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.core.designsystem.component.NetworkImage
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme
import com.sundaegukbap.banchango.core.designsystem.theme.Orange
import com.sundaegukbap.banchango.core.designsystem.theme.White
import com.sundaegukbap.banchango.core.designsystem.theme.lightGray
import com.sundaegukbap.banchango.feature.home.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun IngredientItem(
    ingredient: Ingredient,
    createdAt: LocalDateTime,
    expirationDate: LocalDateTime,
    modifier: Modifier = Modifier,
) {
    val dDay = ChronoUnit.DAYS.between(
        LocalDateTime.now(),
        expirationDate,
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors().copy(containerColor = lightGray)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        NetworkImage(
            url = ingredient.image,
            Modifier
                .size(70.dp)
                .clip(CircleShape)
                .align(CenterHorizontally)
        )
        Card(
            colors = CardDefaults.cardColors().copy(containerColor = White),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(8.dp)
                .background(Color.White, RoundedCornerShape(20.dp))
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 4.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = ingredient.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "D - $dDay",
                    fontSize = 12.sp,
                    color = Orange,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(TopEnd)
                )
                Text(
                    modifier = Modifier.align(BottomStart),
                    text = "등록일 - ${createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}",
                    fontSize = 10.sp,
                    lineHeight = 10.sp,
                )
            }


        }
    }
}

@Preview
@Composable
private fun IngredientItemPreview() {
    BanchangoTheme {
        IngredientItem(
            modifier = Modifier.width(200.dp),
            ingredient = Ingredient(
                id = 1,
                name = "Ingredient",
                kind = IngredientKind.VEGETABLE,
                image = "",
            ),
            createdAt = LocalDateTime.now(),
            expirationDate = LocalDateTime.now(),
        )
    }
}