package com.sundaegukbap.banchango.ai.application;

import com.sundaegukbap.banchango.ai.dto.AiRecipeRecommendRequest;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AiRecipeRecommendService {
    @Value("${api.aiBaseUrl}")
    private String aiBaseUrl;
    private final RestTemplate restTemplate;

    public AiRecipeRecommendService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRecommendedRecipesFromAI(String category, List<Ingredient> ingredientList) {
        AiRecipeRecommendRequest request = AiRecipeRecommendRequest.of(ingredientList);

        return restTemplate.postForObject(aiBaseUrl + "/recommend", request, String.class);
    }
}
