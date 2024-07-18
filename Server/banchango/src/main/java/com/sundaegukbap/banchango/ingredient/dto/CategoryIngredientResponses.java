package com.sundaegukbap.banchango.ingredient.dto;

import java.util.List;

public record CategoryIngredientResponses(
        List<CategoryIngredientResponse> categoryIngredientResponses
) {
    public static CategoryIngredientResponses of(List<CategoryIngredientResponse> categoryIngredientResponseList){
        return new CategoryIngredientResponses(categoryIngredientResponseList);
    }
}
