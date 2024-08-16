package com.sundaegukbap.banchango.ingredient.dto.dto;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;

public record IngredientDto(
        Long id,
        String name,
        String kind,
        String image
) {
    public static IngredientDto of(Ingredient ingredient){
        return new IngredientDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getKind(),
                ingredient.getImage()
        );
    }
}
