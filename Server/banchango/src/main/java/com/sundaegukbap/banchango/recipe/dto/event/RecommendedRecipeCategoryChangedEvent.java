package com.sundaegukbap.banchango.recipe.dto.event;

import com.sundaegukbap.banchango.recipe.domain.RecipeCategory;

public record RecommendedRecipeCategoryChangedEvent(
        Long userId,
        RecipeCategory recipeCategory
) {
}
