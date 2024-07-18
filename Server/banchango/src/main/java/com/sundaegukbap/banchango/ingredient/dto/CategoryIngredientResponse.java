package com.sundaegukbap.banchango.ingredient.dto;

import java.util.HashMap;

public record CategoryIngredientResponse(
        String kind,
        IngredientDetailResponses ingredientDetailResponses
) {
    public static CategoryIngredientResponse of(String kind, IngredientDetailResponses ingredientDetailResponses){
        return new CategoryIngredientResponse(
                kind,
                ingredientDetailResponses
        );
    }
}
