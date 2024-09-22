package com.sundaegukbap.banchango.core.data.repository

import com.sundaegukbap.banchango.Container
import com.sundaegukbap.banchango.ContainerIngredient
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.core.data.repository.api.IngredientRepository
import java.time.LocalDateTime
import javax.inject.Inject

internal class FakeIngredientRepository @Inject constructor() : IngredientRepository {
    override suspend fun getIngredientContainers(): Result<List<ContainerIngredient>> {
        return Result.success(
            listOf(
                ContainerIngredient(
                    1,
                    Container(1, "Container 1"),
                    Ingredient(1, "Ingredient 1", IngredientKind.SAUCE, "Ingredient 1 Image"),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1)
                ),
                ContainerIngredient(
                    2,
                    Container(2, "Container 2"),
                    Ingredient(2, "Ingredient 2", IngredientKind.VEGETABLE, "Ingredient 2 Image"),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(2)
                ),
                ContainerIngredient(
                    3,
                    Container(2, "Container 2"),
                    Ingredient(3, "Ingredient 3", IngredientKind.MEAT, "Ingredient 3 Image"),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(3)
                ),
                ContainerIngredient(
                    4,
                    Container(2, "Container 2"),
                    Ingredient(4, "Ingredient 4", IngredientKind.VEGETABLE, "Ingredient 4 Image"),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(4)
                ),
                ContainerIngredient(
                    5,
                    Container(1, "Container 1"),
                    Ingredient(5, "Ingredient 5", IngredientKind.SAUCE, "Ingredient 5 Image"),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(5)
                ),
            )
        )
    }

    override suspend fun addIngredientContainer(containerName: String): Result<Unit> {
        return Result.success(Unit)
    }
}