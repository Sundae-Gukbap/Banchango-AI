package com.sundaegukbap.banchango.ai.dto;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

public record AiRecipeRecommendRequest(
        List<Long> ingredients
) {
    public static AiRecipeRecommendRequest of(List<Ingredient> ingredients) {
        List<Long> ingredientIds = ingredients.stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList());

        return new AiRecipeRecommendRequest(ingredientIds);
    }
}
