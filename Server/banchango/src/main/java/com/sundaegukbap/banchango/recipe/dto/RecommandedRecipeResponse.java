package com.sundaegukbap.banchango.recipe.dto;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.dto.dto.IngredientDtos;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.dto.dto.RecipeDto;

import java.util.List;

public record RecommandedRecipeResponse(
        RecipeDto recipe,
        IngredientDtos have,
        IngredientDtos need) {
    public static RecommandedRecipeResponse of(Recipe recipe, List<Ingredient> have, List<Ingredient> need){
        return new RecommandedRecipeResponse(
                RecipeDto.of(recipe),
                IngredientDtos.of(have),
                IngredientDtos.of(need)
        );
    }
}