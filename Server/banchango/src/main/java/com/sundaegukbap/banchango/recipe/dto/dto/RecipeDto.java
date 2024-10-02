package com.sundaegukbap.banchango.recipe.dto.dto;

import com.sundaegukbap.banchango.recipe.domain.RecipeDifficulty;
import com.sundaegukbap.banchango.recipe.domain.Recipe;

public record RecipeDto(
        Long id,
        String name,
        String introduction,
        String image,
        String link,
        int servings,
        int cookingTime,
        RecipeDifficulty recipeDifficulty
) {
    public static RecipeDto of(Recipe recipe){
        return new RecipeDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getIntroduction(),
                recipe.getImage1(),
                recipe.getLink(),
                recipe.getServings(),
                recipe.getCookingTime(),
                recipe.getRecipeDifficulty()
        );
    }
}
