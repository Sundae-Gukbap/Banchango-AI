package com.sundaegukbap.banchango.ai.dto;

import java.util.List;

public record AiRecipeRecommendResponse(
        List<Long> recommended_recipes
) {
}
