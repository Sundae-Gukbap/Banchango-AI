package com.sundaegukbap.banchango.recipe.dto;

import com.sundaegukbap.banchango.recipe.domain.Difficulty;
import com.sundaegukbap.banchango.recipe.domain.Recipe;

import java.util.List;

public record RecipeDetailResponse(
    Long id,
    String name,
    String introduction,
    String image,
    String link,
    List<String> have,
    List<String> need,
    int servings,
    int cookingTime,
    boolean isBookmarked,
    Difficulty difficulty) {
    public static RecipeDetailResponse of(Recipe recipe, List<String> have, List<String> need, boolean isBookmarked) {
        return new RecipeDetailResponse(
                recipe.getId(),
                recipe.getName(),
                recipe.getIntroduction(),
                recipe.getImage(),
                recipe.getLink(),
                have,
                need,
                recipe.getServings(),
                recipe.getCookingTime(),
                isBookmarked,
                recipe.getDifficulty()
        );
    }
}
