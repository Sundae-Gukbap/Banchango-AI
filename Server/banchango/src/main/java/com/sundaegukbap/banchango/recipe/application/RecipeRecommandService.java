package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.dto.RecipeDetailResponse;
import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeRecommandService {
    private final RecipeRepository recipeRepository;

    public RecipeRecommandService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDetailResponse> getRecipes(Long userId) {
        //AI에게서 레시피들을 받아 온다. List<Long> recipeIds
        List<Long> recipeIds = new ArrayList<>();
        List<Recipe> recipes = recipeRepository.findAllById(recipeIds);

        //recipe를 순회하면서 user와 비교

        List<RecipeDetailResponse> response = new ArrayList<>();

        return response;
    }
}
