package com.sundaegukbap.banchango.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sundaegukbap.banchango.Container
import com.sundaegukbap.banchango.ContainerIngredient
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientContainer
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.KindIngredientContainer
import com.sundaegukbap.banchango.core.designsystem.theme.BanchangoTheme
import com.sundaegukbap.banchango.core.designsystem.theme.Gray
import com.sundaegukbap.banchango.core.designsystem.theme.LightOrange
import com.sundaegukbap.banchango.core.designsystem.theme.Orange
import com.sundaegukbap.banchango.core.designsystem.theme.White
import com.sundaegukbap.banchango.core.designsystem.theme.lightGray
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
fun HomeRoute(
    padding: PaddingValues,
    onChangeStatusBarColor: (color: Color, darkIcons: Boolean) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val ingredientContainers by viewModel.ingredientContainers.collectAsStateWithLifecycle()

    HomeScreen(
        padding = padding,
        ingredientContainers = ingredientContainers,
        onChangeStatusBarColor = onChangeStatusBarColor
    )
}

@Composable
private fun HomeScreen(
    padding: PaddingValues,
    ingredientContainers: List<IngredientContainer>,
    onChangeStatusBarColor: (color: Color, darkIcons: Boolean) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
    ) {
        items(ingredientContainers) { ingredientContainer ->
            val isEven = ingredientContainers.indexOf(ingredientContainer) % 2 == 0
            val containerColor = if (isEven) LightOrange else Gray
            val itemColor = if (isEven) Gray else White
            val buttonColor = if (isEven) White else Gray

            ElevatedCard(
                colors = CardDefaults.elevatedCardColors().copy(containerColor = containerColor),
                elevation = CardDefaults.elevatedCardElevation(2.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp, horizontal = 8.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(0.4f),
                        fontWeight = FontWeight.Bold,
                        text = ingredientContainer.container.name
                    )
                    Spacer(modifier = Modifier.weight(0.5f))
                    Icon(
                        modifier = Modifier.weight(0.1f),
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null
                    )
                }
                val kindIngredients = ingredientContainer.kindIngredientContainers
                val totalIngredients = kindIngredients.size


                // Creating rows of two IngredientItems
                for (index in 0 until totalIngredients step 2) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween // Distributes items evenly
                    ) {
                        // First IngredientItem
                        IngredientItem(
                            containerColor = itemColor,
                            kindIngredientContainer = kindIngredients[index],
                            buttonColor = buttonColor,
                            modifier = Modifier.width(150.dp) // Fixed width for consistent size
                        )

                        // Check for the second item
                        if (index + 1 < totalIngredients) {
                            Spacer(modifier = Modifier.width(20.dp)) // Spacer between items
                            IngredientItem(
                                kindIngredientContainer = kindIngredients[index + 1],
                                modifier = Modifier.width(150.dp), // Fixed width for consistent size
                                containerColor = itemColor,
                                buttonColor = buttonColor,
                            )
                        } else {
                            // If the second item does not exist, add a spacer for alignment
                            Spacer(modifier = Modifier.weight(1f)) // Takes remaining space
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item { AddContainerButton(if (ingredientContainers.size % 2 == 0) LightOrange else Gray) }
    }
}

@Composable
private fun AddContainerButton(
    containerColor: Color,
) {
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(containerColor = containerColor),
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IngredientItem(
    containerColor: Color,
    buttonColor: Color,
    kindIngredientContainer: KindIngredientContainer,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = containerColor),
        modifier = modifier
            .height(106.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            val ingredients = kindIngredientContainer.ingredients
            Row {
                Text(
                    text = kindIngredientContainer.kind.label,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(0.3f))
                Text(
                    modifier = Modifier.background(
                        color = buttonColor,
                        shape = RoundedCornerShape(20.dp)
                    ).padding(horizontal = 16.dp, vertical = 4.dp),
                    text = ingredients.size.toString() + "개",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Orange
                )
            }

            // 2개까지만 표시하고, 2개 이상일 경우 ... 표시
            ingredients.subList(0, minOf(ingredients.size, 2)).forEach { ingredient ->
                val dDay = ChronoUnit.DAYS.between(
                    LocalDateTime.now(),
                    ingredient.expirationDate,
                )
                Row {
                    Text(
                        text = ingredient.ingredient.name,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "D - $dDay", style = MaterialTheme.typography.bodySmall)
                }
            }
            if (ingredients.size > 2) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "...",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewIngredientItem() {
    BanchangoTheme {
        IngredientItem(
            containerColor = White,
            buttonColor = Gray,
            kindIngredientContainer = KindIngredientContainer(
                IngredientKind.VEGETABLE,
                listOf(
                    ContainerIngredient(
                        1,
                        Container(1, "냉장 1"),
                        Ingredient(1, "상추", IngredientKind.VEGETABLE, ""),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                    ),
                    ContainerIngredient(
                        1,
                        Container(1, "냉장 1"),
                        Ingredient(1, "상추", IngredientKind.VEGETABLE, ""),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                    )
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    BanchangoTheme {
        HomeScreen(
            padding = PaddingValues(0.dp),
            onChangeStatusBarColor = { _, _ -> },
            ingredientContainers = listOf(
                IngredientContainer(
                    Container(1, "냉장 1"),
                    listOf(
                        KindIngredientContainer(
                            IngredientKind.VEGETABLE, listOf(
                                ContainerIngredient(
                                    1,
                                    Container(1, "냉장 1"),
                                    Ingredient(1, "상추", IngredientKind.VEGETABLE, ""),
                                    LocalDateTime.now(),
                                    LocalDateTime.now()
                                ),
                                ContainerIngredient(
                                    1,
                                    Container(1, "냉장 1"),
                                    Ingredient(1, "상추", IngredientKind.VEGETABLE, ""),
                                    LocalDateTime.now(),
                                    LocalDateTime.now()
                                )
                            )
                        )
                    )
                ),
                IngredientContainer(
                    Container(1, "냉장 1"),
                    listOf(
                        KindIngredientContainer(
                            IngredientKind.VEGETABLE, listOf(
                                ContainerIngredient(
                                    1,
                                    Container(1, "냉장 1"),
                                    Ingredient(1, "상추", IngredientKind.VEGETABLE, ""),
                                    LocalDateTime.now(),
                                    LocalDateTime.now()
                                )
                            )
                        )
                    )
                )
            )
        )
    }
}

