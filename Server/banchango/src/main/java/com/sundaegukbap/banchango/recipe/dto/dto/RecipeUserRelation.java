package com.sundaegukbap.banchango.recipe.dto.dto;

import java.util.List;

public record RecipeUserRelation(
        List<String> have,
        List<String> need,
        boolean isBookmarked) {
    public static RecipeUserRelation of(List<String> have,
                                        List<String> need,
                                        boolean isBookmarked){
        return new RecipeUserRelation(have, need, isBookmarked);
    }
}
