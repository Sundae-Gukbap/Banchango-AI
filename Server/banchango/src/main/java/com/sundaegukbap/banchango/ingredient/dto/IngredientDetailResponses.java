package com.sundaegukbap.banchango.ingredient.dto;

import java.util.List;

public record IngredientDetailResponses (
        List<IngredientDetailResponse> ingredientDetailResponses
){
    public static IngredientDetailResponses of(List<IngredientDetailResponse> ingredientDetailResponseList) {
        return new IngredientDetailResponses(
                ingredientDetailResponseList
        );
    }
}


