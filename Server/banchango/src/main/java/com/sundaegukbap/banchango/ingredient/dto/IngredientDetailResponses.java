package com.sundaegukbap.banchango.ingredient.dto;

import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.dto.RecipeDetailResponse;

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


