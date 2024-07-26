package com.sundaegukbap.banchango.ingredient.dto.dto;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.container.dto.dto.ContainerDto;
import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;

import java.time.LocalDateTime;

public record ContainerIngredientDto(
        Long containerIngredientId,
        ContainerDto containerDto,
        IngredientDto ingredientDto,
        LocalDateTime createdAt,
        LocalDateTime expirationDate
) {
    public static ContainerIngredientDto of(Ingredient ingredient, ContainerIngredient containerIngredient, Container container){
        return new ContainerIngredientDto(
                containerIngredient.getId(),
                ContainerDto.of(container),
                IngredientDto.of(ingredient),
                containerIngredient.getCreatedAt(),
                containerIngredient.getExpriationDate());
    }
}
