package com.sundaegukbap.banchango.ingredient.dto;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;

import java.time.LocalDateTime;

public record IngredientInsertRequest(
        Long containerId,
        Long ingredientId,
        LocalDateTime expirationDate
) {
    public ContainerIngredient toEntity(Container container, Ingredient ingredient){
        return ContainerIngredient.builder()
                .id(null)
                .container(container)
                .ingredient(ingredient)
                .createdAt(null)
                .expriationDate(this.expirationDate)
                .build();
    }
}
