package com.sundaegukbap.banchango.ai.application;

import com.sundaegukbap.banchango.ai.dto.AiRecipeRecommendRequest;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
public class AiRecipeRecommendService {
    private static final String AI_BASE_URL = "http://34.222.135.30:8000";
    private final RestTemplate restTemplate;

    public String getRecommendedRecipesFromAI(String category, List<Ingredient> ingredientList) {
        AiRecipeRecommendRequest request = AiRecipeRecommendRequest.of(ingredientList);

        return restTemplate.postForObject(AI_BASE_URL + "/recommend", request, String.class);
    }
}
