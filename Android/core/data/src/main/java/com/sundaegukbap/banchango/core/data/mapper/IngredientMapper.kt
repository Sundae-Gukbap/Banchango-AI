package com.sundaegukbap.banchango.core.data.mapper

import com.sundaegukbap.banchango.Container
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientContainer
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.core.data.api.model.ContainerDto
import com.sundaegukbap.banchango.core.data.api.model.ContainerIngredientDto
import com.sundaegukbap.banchango.core.data.api.model.IngredientDto

internal fun ContainerIngredientDto.toData() = IngredientContainer(
    id = containerIngredientId,
    container = containerDto.toData(),
    ingredient = ingredientDto.toData(),
    createdAt = createdAt,
    expirationDate = expirationDate
)

internal fun ContainerDto.toData() = Container(
    id = containerId,
    name = containerName
)

internal fun IngredientDto.toData() = Ingredient(
    id = ingredientId,
    name = name,
    kind = when (kind) {
        "육류" -> IngredientKind.MEAT
        "해산물" -> IngredientKind.SEAFOOD
        "채소" -> IngredientKind.VEGETABLE
        "과일" -> IngredientKind.FRUIT
        "기타" -> IngredientKind.ETC
        else -> IngredientKind.ETC
    },
    image = image
)