package com.sundaegukbap.banchango.recipe.dto;

import java.util.List;

public record RecommandedRecipeResponses(
        List<RecommandedRecipeResponse> recommandedRecipeResponses) {
    public static RecommandedRecipeResponses of(List<RecommandedRecipeResponse> recommandedRecipeResponseList){
        return new RecommandedRecipeResponses(recommandedRecipeResponseList);
    }
}
