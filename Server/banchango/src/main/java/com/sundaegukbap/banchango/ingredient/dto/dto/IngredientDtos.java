package com.sundaegukbap.banchango.ingredient.dto.dto;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

public record IngredientDtos(
        List<IngredientDto> ingredientDtos
) {
    public static IngredientDtos of(List<Ingredient> ingredientList){
        return new IngredientDtos(ingredientList.stream()
                .map(IngredientDto::of)
                .collect(Collectors.toList()));
    }
}
