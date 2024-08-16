package com.sundaegukbap.banchango.recipe.dto.dto;

import com.sundaegukbap.banchango.recipe.domain.Difficulty;
import com.sundaegukbap.banchango.recipe.domain.Recipe;

public record RecipeDto(
        Long id,
        String name,
        String introduction,
        String image1,
        String link,
        int servings,
        int cookingTime,
        Difficulty difficulty
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
                recipe.getDifficulty()
        );
    }
}
