package com.sundaegukbap.banchango.core.data.mapper

import com.sundaegukbap.banchango.Container
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.ContainerIngredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.core.data.api.model.ContainerDto
import com.sundaegukbap.banchango.core.data.api.model.ContainerIngredientDto
import com.sundaegukbap.banchango.core.data.api.model.IngredientDto
import java.time.LocalDateTime

internal fun ContainerIngredientDto.toData() = ContainerIngredient(
    id = containerIngredientId,
    container = containerDto.toData(),
    ingredient = ingredientDto.toData(),
    createdAt = LocalDateTime.parse(createdAt),
    expirationDate = LocalDateTime.parse(expirationDate)
)

internal fun ContainerDto.toData() = Container(
    id = containerId,
    name = containerName
)

internal fun IngredientDto.toData() = Ingredient(
    id = id,
    name = name,
    kind = when (kind) {
        "육류" -> IngredientKind.MEAT
        "해산물" -> IngredientKind.SEAFOOD
        "채소" -> IngredientKind.VEGETABLE
        "과일" -> IngredientKind.FRUIT
        "기타" -> IngredientKind.ETC
        else -> IngredientKind.ETC
    },
    image = image ?: ""
)