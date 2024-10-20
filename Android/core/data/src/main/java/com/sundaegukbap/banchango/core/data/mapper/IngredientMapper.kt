package com.sundaegukbap.banchango.core.data.mapper

import com.sundaegukbap.banchango.Container
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.ContainerIngredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.core.data.api.model.ContainerDto
import com.sundaegukbap.banchango.core.data.api.model.ContainerIngredientDto
import com.sundaegukbap.banchango.core.data.api.model.IngredientDto
import com.sundaegukbap.banchango.core.data.entity.IngredientEntity
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
    kind = IngredientKind.entries.find { it.label == kind } ?: IngredientKind.ETC,
    image = image ?: ""
)

internal fun IngredientEntity.toData() = Ingredient(
    id = id,
    name = name ?: "",
    kind = IngredientKind.entries.find { it.label == kind } ?: IngredientKind.ETC,
    image = image ?: ""
)